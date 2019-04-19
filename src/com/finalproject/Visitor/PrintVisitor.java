package com.finalproject.Visitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.finalproject.AST.*;

public class PrintVisitor implements Visitor {

	private BufferedWriter out;

	public PrintVisitor(String outputFile) {
		try {
			this.out = new BufferedWriter(new FileWriter(outputFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void visit(Identifier id) {
		if (id.getName() != null) {
			System.out.print(id.getName());
			try {
				this.out.write(id.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void visit(VarDecl var) {
		if (var.getType() != null) {
			var.getType().accept(this);
		}
		System.out.print(" ");
		try {
			this.out.write(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (var.getId() != null) {
			var.getId().accept(this);
		}
		System.out.print(";");
		try {
			this.out.write(";");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(VarDeclList varList) {
		if (varList.getList() != null) {
			varList.accept(this);
		}
		System.out.println();
	}

	@Override
	public void visit(Declaration declaration) {
		if (declaration.getList() != null) {
			int i = 0;
			for (VarDeclList varDeclList : declaration.getList()) {
				for (VarDecl varDecl : varDeclList.getList()) {
					if (i < varDeclList.size() - 1) {
						System.out.println("child: " + varDecl.getId());
						System.out.println("child: COMMAnumber [,]");

						try {
							this.out.write("child: " + varDecl.getId() + "\r\n");
							this.out.write("child: COMMAnumber [,]" + "\r\n");

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						i++;
					} else {
						System.out.println("child: " + varDecl.getId());
						System.out.print("child: SEMInumber [;]");

						try {
							this.out.write("child: " + varDecl.getId() + "\r\n");
							this.out.write("child: SEMInumber [;]");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}
		}
		System.out.println();
		try {
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Program program) {
		System.out.println("Child: PROGRAMname [program]");

		try {
			this.out.write("Child: PROGRAMname [program]\r\n");
			if (program.getName() != null) {
				System.out.print("child: PIDnumber ");
				this.out.write("child: PIDnumber ");
				program.getName().accept(this);
				System.out.println();
				this.out.write("\r\n");
			}
			System.out.println();
			if (program.getList() != null) {
				System.out.println("child: VARnumber [var]");
				this.out.write("child: VARnumber [var]\r\n");
				program.getList().accept(this);
			}

			if (program.getStm() != null) {
				if (program.getStm().size() == 1) {
					System.out.println("child: <Statement>");
					this.out.write("child: <Statement>\r\n");
					program.getStm().elementAt(0).accept(this);
				} else {
					System.out.println("child: <StatementList>");
					this.out.write("child: <StatementList>\r\n");
					for (int i = 0; i < program.getStm().size(); i++) {
						System.out.println("child: <Statement" + (i + 1) + ">");
						this.out.write("child: <Statement" + (i + 1) + ">\r\n");
						program.getStm().elementAt(i).accept(this);
					}
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(VarType varType) {
		// TODO Auto-generated method stub
		System.out.println("Child: VARnumber [var]");
		try {
			this.out.write("Child: VARnumber [var]\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Block blockStm) {
		System.out.println("{");
		try {
			this.out.write("{\r\n");
			if (blockStm.getStms() != null) {
				for (int i = 0; i < blockStm.getStms().size(); i++) {
					if (blockStm.getStms().elementAt(i) == null)
						continue;

					blockStm.getStms().elementAt(i).accept(this);
					this.out.write("\r\n");
					System.out.println();
				}
			}

			System.out.println("\t\t}");
			this.out.write("\t\t}\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(IfStatement ifStm) {
		try {
			System.out.println("child: <IfStatement>");
			System.out.println("child: IFnumber [if]");
			this.out.write("child: <IfStatement>\r\n");
			this.out.write("child: IFnumber [if]\r\n");
			if (ifStm.getCondExp() != null) {
				System.out.print("child: <condExp> ");
				this.out.write("child: <condExp> ");
				ifStm.getCondExp().accept(this);
				this.out.write("\r\n");
				System.out.println();
			}
			System.out.println("child: THENnumber [then]");
			this.out.write("child: THENnumber [then]\r\n");

			if (ifStm.getTrueStm() != null) {
				System.out.print("child: <TrueExp> ");
				this.out.write("child: <TrueExp> ");
				ifStm.getTrueStm().accept(this);
				this.out.write("\r\n");
				System.out.println();
			}
			if (ifStm.getFalseStm() != null) {
				System.out.println("child: ELSEnumber [else]");
				System.out.println("child: <FalseExp> ");
				this.out.write("child: ELSEnumber [else]\r\nchild: <FalseExp> ");
				ifStm.getFalseStm().accept(this);
				
				System.out.println("child: ENDIFnumber [endif]");
				this.out.write("child: ENDIFnumber [endif]\r\n");
			} else {
				System.out.println("child: ENDIFnumber [endif]");
				this.out.write("child: ENDIFnumber [endif]\r\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(WhileStatement whileStm) {
		try {
			System.out.println("child: WHILEnumber [while]");
			this.out.write("child: WHILEnumber [while]\r\n");
			if (whileStm.getCondExp() != null) {
				System.out.print("child: <condExp>");
				this.out.write("child: <condExp>");
				whileStm.getCondExp().accept(this);
			}
			System.out.println("Child: LOOPnumber [loop]");
			this.out.write("Child: LOOPnumber [loop]\r\n");
			if (whileStm.getStm() != null) {
				whileStm.getStm().accept(this);
			}
			System.out.print("Child: ENDLOOPnumber [endloop]");
			this.out.write("Child: ENDLOOPnumber [endloop]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Assign assignStm) {
		System.out.println("child: <Assign>");
		try {
			this.out.write("child: <Assign>\r\n");
			if (assignStm.getId() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				assignStm.getId().accept(this);
				this.out.write("\r\n");
				System.out.println();
			}
			System.out.println("child: COLEnumber [:=]");
			this.out.write("child: COLEnumber [:=]\r\n");
			if (assignStm.getValue() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				assignStm.getValue().accept(this);
				this.out.write("\r\n");
				System.out.println();
			}
			System.out.print("child: SEMInumber [;]");
			this.out.write("child: SEMInumber [;]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(And andExp) {
		try {
			this.out.write("child: <AND>\r\n");
			System.out.println("child: <And>");
			if (andExp.getLHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getLHS().accept(this);
				this.out.write("\r\n");
			}
			System.out.println("Child: ANDnumber  [&&]");
			this.out.write("Child: ANDnumber [&&]\r\n");
			if (andExp.getRHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getRHS().accept(this);
				
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Or andExp) {
		try {
			this.out.write("child: <OR>\r\n");
			System.out.println("child: <Or>");
			if (andExp.getLHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getLHS().accept(this);
			}
			System.out.println("Child: ORnumber [||]");
			this.out.write("Child: ORnumber [||]\r\n");
			if (andExp.getRHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getRHS().accept(this);
				
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Equal andExp) {
		try {
			this.out.write("child: <EQUAL>\r\n");
			System.out.println("child: <Equal>");
			if (andExp.getLHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getLHS().accept(this);
			}
			System.out.println("child: EQnumber [==]");
			this.out.write("child: EQnumber [==]\r\n");
			if (andExp.getRHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getRHS().accept(this);
				
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(NotEqual andExp) {
		try {
			this.out.write("child: <NOTEQUAL>\r\n");
			System.out.print("child: <NotEqual>");
			if (andExp.getLHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getLHS().accept(this);
			}
			System.out.println("Child: NEQnumber [<>]");
			this.out.write("Child: NEQnumber [<>]\r\n");
			if (andExp.getRHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getRHS().accept(this);
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(MoreThan andExp) {
		try {
			this.out.write("child: <MORETHAN>");
			System.out.println("Child: <MoreThan>");
			if (andExp.getLHS() != null) {
				this.out.write("child: ");
				andExp.getLHS().accept(this);
			}
			System.out.print("child: GTnumber [>]");
			if (andExp.getRHS() != null) {
				this.out.write("child: ");
				andExp.getRHS().accept(this);
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(MoreThanEqual andExp) {
		try {
			this.out.write("child: <MORETHANEQUAL>\r\n");
			System.out.println(" child: <MoreThanEqual>");
			if (andExp.getLHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getLHS().accept(this);
				System.out.println();
				this.out.write("\r\n");
			}
			System.out.println("child: GEnumber [>=] ");
			this.out.write("child: GEnumber [>=]\r\n");
			if (andExp.getRHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getRHS().accept(this);
				System.out.println();
				this.out.write("\r\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(LessThan lessThanExp) {
		try {
			this.out.write("child: <LESSTHAN>\r\n");
			System.out.println("Child: <LessThan>");
			if (lessThanExp.getLHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				lessThanExp.getLHS().accept(this);
			}
			System.out.println("child: LTnumber [<]");
			this.out.write("child: LTnumber [<]\r\n");
			if (lessThanExp.getRHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				lessThanExp.getRHS().accept(this);
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(LessThanEqual andExp) {
		try {
			this.out.write("child: <LESSTHANEQUAL>\r\n");
			System.out.println(" child: <LessThanEqualExp>");
			if (andExp.getLHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getLHS().accept(this);
				System.out.println();
				this.out.write("\r\n");
			}
			System.out.println("child: LTnumber [<=] ");
			this.out.write("child: LTnumber [<=]\r\n");
			if (andExp.getRHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				andExp.getRHS().accept(this);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Plus plusExp) {
		try {
			this.out.write("child: <PLUS>\r\n");
			System.out.println("child: <PLusExp>");
			if (plusExp.getLHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				plusExp.getLHS().accept(this);
				System.out.println();
				this.out.write("\r\n");
			}
			System.out.println("child: PLUSnumber [+]");
			this.out.write("child: PLUSnumber [+]\r\n");
			if (plusExp.getRHS() != null) {
				System.out.print("Child: ");
				this.out.write("child: ");
				plusExp.getRHS().accept(this);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Minus minusExp) {
		try {
			this.out.write("child: <MINUS>\r\n");
			System.out.print("child: <MinusExp>");
			if (minusExp.getLHS() != null) {
				System.out.print("Child: ");
				minusExp.getLHS().accept(this);
			}
			System.out.println("child: MINUSnumber  [-]");
			this.out.write("child: MINUSnumber [-]\r\n");
			if (minusExp.getRHS() != null) {
				System.out.print("Child: ");
				minusExp.getRHS().accept(this);
				
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Times timesExp) {
		try {
			this.out.write("child: <TIMES>\r\n");
			System.out.print("child: <TimeExp>");
			if (timesExp.getLHS() != null) {
				System.out.print("Child: ");
				timesExp.getLHS().accept(this);
			}
			System.out.print("child: TIMESnumber [*]");
			this.out.write("child: TIMESnumber [*]\r\n");
			if (timesExp.getRHS() != null) {
				System.out.print("Child: ");
				timesExp.getRHS().accept(this);
				
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Divide timesExp) {
		try {
			this.out.write("child: <DIVIDE>\r\n");
			System.out.println("child: <Divide>");
			if (timesExp.getLHS() != null) {
				System.out.print("Child: ");
				timesExp.getLHS().accept(this);
				
			}
			System.out.print("child: DIVIDEnumber [/]");
			this.out.write("child: DIVIDEnumber [/]\r\n");
			if (timesExp.getRHS() != null) {
				System.out.print("Child: ");
				timesExp.getRHS().accept(this);
				
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Modules timesExp) {
		try {
			this.out.write("child: <MODULE>\r\n");
			if (timesExp.getLHS() != null) {
				this.out.write("child: ");
				timesExp.getLHS().accept(this);
			}
			System.out.print(" % ");
			this.out.write("child: MODnumber [%]\r\n");
			if (timesExp.getRHS() != null) {
				this.out.write("child: ");
				timesExp.getRHS().accept(this);
			}
			System.out.println();
			this.out.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(IntegerLiteral intLiteral) {
		System.out.print(intLiteral.getValue());
		try {
			this.out.write(String.valueOf(intLiteral.getValue()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void visit(FloatLiteral floatLiteral) {
		System.out.print(floatLiteral.getValue());
		try {
			this.out.write(String.valueOf(floatLiteral.getValue()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void visit(IdentifierExpression identExp) {
		if (identExp.getName() != null) {
			System.out.print(identExp.getName());
			try {
				this.out.write(identExp.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void visit(Not notExp) {
		System.out.print("!");
		if (notExp.getExp() != null) {
			notExp.getExp().accept(this);
		}
	}

	@Override
	public void visit(Negative negExp) {
		System.out.print("-");
		if (negExp.getExp() != null) {
			negExp.getExp().accept(this);
		}
	}

	public void visit(CharLiteral charLiteral) {
		System.out.println(charLiteral.getValue());
		try {
			this.out.write(charLiteral.getValue());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeFile() {
		try {
			this.out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeFile(String input) {
		try {
			this.out.write(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
