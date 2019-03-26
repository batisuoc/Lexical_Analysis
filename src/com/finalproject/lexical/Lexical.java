package com.finalproject.lexical;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//lop chua cac phuong thuc xu ly tu vung
public class Lexical {
	
	//ham doc 1 file, xu ly tung dong cua 1 file
	//su dung BufferedReader đe doc tung dong chua trong file
	//input: string fileInput - ten cua duong dan chua file
	public void xuLyFile(String fileInput) throws IOException
	{
		int numLine = 1;
		BufferedReader in = new BufferedReader(new FileReader(fileInput));
		
		String line;
		while((line = in.readLine()) != null)
		{
			//System.out.println(line + " " + numLine);
			scanLine(line, numLine);
			numLine++;
		}
		in.close();
	}
	
	//ham xu ly cac tu cua 1 dong
	//input: 1 dong doc tu file, chi so dong hien tai
	//output: 1 file ghi ket qua
	private void scanLine(String line, int numLine)
	{
		 String[] words = line.split("\\s");
		 for(int i = 0;i< words.length;i++)
		 {
			 System.out.println(words[i] + " " + numLine);
			  //xu ly truong hop: cac so, cac chu khong co di lien voi ky tu dac biet
			 if(words[i].matches("[a-zA-Z0-9]*[\\w]*[+,;:=!\\\\(\\)><.-]+") == false)
			 {
				 //truong hop: la so
				 //can phai kiem tra no la so nguyen hay so thuc
				 if(words[i].matches("\\d") == true)
				 {
					 //string.matches("-?\\d+(\\.\\d+)?")
					 System.out.println(words[i]+" number "+ numLine +" "+ numLine + " pos pos");
				 }
				 
				 else
				 {
					 //truong hop: la chu
					 // can phai kiem tra la keywords, hay identifier
					 if(words[i].matches("\\s+") == true)
					 {
						 continue;
					 }
					
				     System.out.println(words[i]+" words " + numLine +" "+ numLine + " pos pos");
					 
				 }	  
			 }
			 else
			 {
				 //xu ly truong hop
				 //truong hop a++, a+b, (a == 5), -a, a;
				 //va truong hop la ky tu dac biet
				 if(words[i].matches("[+,;:=!\\\\(\\)><.-]+") == true)
				 {
					 switch(words[i])
					 {
					    case ";":
					    {
					    	System.out.println(words[i]+" SEMInumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case ":":
					    {
					    	System.out.println(words[i]+" COLONnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case ",":
					    {
					    	System.out.println(words[i]+" COMMAnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case ".":
					    {
					    	System.out.println(words[i]+" DOTnumber" + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case "(":
					    {
					    	System.out.println(words[i]+" LPARENnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case ")":
					    {
					    	System.out.println(words[i]+" RPARENnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case "<":
					    {
					    	System.out.println(words[i]+" LTnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case ">":
					    {
					    	System.out.println(words[i]+" GTnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case "=":
					    {
					    	System.out.println(words[i]+" EQnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case "-":
					    {
					    	System.out.println(words[i]+" MINUSnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case "+":
					    {
					    	System.out.println(words[i]+" PLUSnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case "*":
					    {
					    	System.out.println(words[i]+" TIMESnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    	
					    case "..":
					    {
					    	System.out.println(words[i]+" DOTDOTnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case ":=":
					    {
					    	System.out.println(words[i]+" COLEQnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case "<=":
					    {
					    	System.out.println(words[i]+" LEnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case ">=":
					    {
					    	System.out.println(words[i]+" GEnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					    case "<>":
					    {
					    	System.out.println(words[i]+" NEnumber " + numLine +" "+ numLine + " pos pos");
					    	break;
					    }
					 }
				 }
				 else
				 { 
					 //System.out.println(words[i]+" XXX " + numLine +" "+ numLine + " pos pos");
					 //xu ly truong hop a+5, a; a&&b;
					 
					// demSO++;  bien nay de kiem soat viec dem phan tich cung 1 so,
					 //vd 34111; se chia thanh 3 4 1 1 1 ;, 
					 //minh chi can xet so 3 sau do hinh thanh so 34111
					 //tranh phai xet cac so dung sau (skip cac so do) de chuyen qua ';'
					 
					 //demChu; bien nay de kiem soat viec phan tich cung 1 chu,
					 //vd xar; se chia thanh x a r ;
					 //minh chi can xet chu x sau do hinh thanh chu xar
					 //tranh phai xet cac chu dung sau (skip cac chu đo) de chuyen qua ';'
					  
					   char[] list = words[i].toCharArray(); // bien thanh mang chu cai vd 34111;
					                                         //se thanh list = [3,4,1,1,1,;]
					   char peek = ' '; //xet chu hien tai
					   int m;
					   int demSO = 0;
					   int demChu = 0;
					   for(int j = 0;j<list.length;j++)
					   {
						   peek = list[j];
						   //System.out.println(peek);
						   
						   //chu
						   if(Character.isLetter(peek) == true)
						   {
							   if(demChu > 0)
							   {
								   continue;
							   }
							   demChu++;
							   StringBuilder b = new StringBuilder();
							   b.append(peek);
							   m = j;
							   m++;
							   peek = list[m];
							  
						       while(Character.isLetter(peek) == true)
						       {
						    	   if(m == list.length)
						    	   {
						    		   break;
						    	   }
						    	   b.append(peek);
						    	   m++;
						    	   peek = list[m];
						    	   demChu++;
						       }
						       
						       if(b != null)
						       {
						    	   System.out.println(b.toString()+" words " + numLine +" "+ numLine + " pos pos");
						    	   peek = ' ';
						       }
						   }
						   
						   //so
						   if(Character.isDigit(peek) == true)
						   {
							   if(demSO > 0)
							   {
								   continue;
							   }
							   int v = 0;
							   m = j;
							   do {
								   demSO++; // bien nay de kiem soat viec dem phan tich cung 1 so, vd 34111
								   v = 10 * v + Character.getNumericValue(peek);
								   m++;
								   peek = list[m];
							   }
							   while(Character.isDigit(peek) == true);
							   
							   if(peek != '.')
							   {
								   System.out.println(String.valueOf(v)+" ICONSTnumber " + numLine +" "+ numLine + " pos pos");
								   peek = ' ';
							   }
							   else
							   {
								   //xet phan sau dau . (phan so thuc)
							   }
							   
						   }
						   
						   //kytudacbiet
						   int demDau = 0;// kiem soat viec phan tich cac dau <= >= <> := ..
						   
						   if(demDau > 0)
						   {
							   peek = ' ';
						   }
						   
						   switch(peek)
							 {
							    case ';':
							    {
							    	System.out.println(peek+" SEMInumber " + numLine +" "+ numLine + " pos pos");
							    	break;
							    }
							    
							    case ':':
							    {
							    	if(list[j+1] == '=')
							    	{
							    		System.out.println(peek+" COLEQnumber " + numLine +" "+ numLine + " pos pos");
							    		demDau++;
							    	}
							    	else
							    	{
							    		System.out.println(peek+" COLONnumber " + numLine +" "+ numLine + " pos pos");
							    	}
							    	
							    	break;
							    }
							    
							    case ',':
							    {
							    	System.out.println(peek+" COMMAnumber " + numLine +" "+ numLine + " pos pos");
							    	break;
							    }
							    
							    case '.':
							    {
							    	if(list[j+1] == '.')
							    	{
							    		System.out.println(peek+" DOTDOTnumber " + numLine +" "+ numLine + " pos pos");
							    		demDau++;
							    	}
							    	else
							    	{
							    		System.out.println(peek+" DOTnumber" + numLine +" "+ numLine + " pos pos");
							    	}
							    	
							    	break;
							    }
							    
							    case '(':
							    {
							    	System.out.println(peek+" LPARENnumber " + numLine +" "+ numLine + " pos pos");
							    	break;
							    }
							    
							    case ')':
							    {
							    	System.out.println(peek+" RPARENnumber " + numLine +" "+ numLine + " pos pos");
							    	break;
							    }
							    
							    case '<':
							    {
							    	if(list[j+1] == '=')
							    	{
							    		System.out.println(peek+" LEnumber " + numLine +" "+ numLine + " pos pos");	
							    		demDau++;
							    	}
							    	else if(list[j+1] == '>')
							    	{
							    		System.out.println(peek+" NEnumber " + numLine +" "+ numLine + " pos pos");
							    		demDau++;
							    	}
							    	else
							    	{
							    		System.out.println(peek+" LTnumber " + numLine +" "+ numLine + " pos pos");
							    	}
							    	
							    	break;
							    }
							    
							    case '>':
							    {
							    	if(list[j+1] == '=')
							    	{
							    		System.out.println(peek+" GEnumber " + numLine +" "+ numLine + " pos pos");
							    		demDau++;
							    	}
							    	else
							    	{
							    		System.out.println(peek+" GTnumber " + numLine +" "+ numLine + " pos pos");
							    	}
							    	
							    	break;
							    }
							    
							    case '=':
							    {
							    	System.out.println(peek+" EQnumber " + numLine +" "+ numLine + " pos pos");
							    	break;
							    }
							    
							    case '-':
							    {
							    	System.out.println(peek+" MINUSnumber " + numLine +" "+ numLine + " pos pos");
							    	break;
							    }
							    
							    case '+':
							    {
							    	if(list[i+1] == '+')
							    	{
							    		demDau++;
							    	}
							    	else
							    	{
							    		System.out.println(peek+" PLUSnumber " + numLine +" "+ numLine + " pos pos");
							    	}
							    	
							    	break;
							    }
							    
							    case '*':
							    {
							    	System.out.println(peek+" TIMESnumber " + numLine +" "+ numLine + " pos pos");
							    	break;
							    }

							 }
						   
					   }
				 }
				 
			 }
		 }	
	}
}
