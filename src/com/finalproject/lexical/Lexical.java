package com.finalproject.lexical;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		 String DOUBLE_Pattern = "[+-]?[\\d]+[.]{1}[\\d]+[eE]+[-+]?[\\d]+";
		 
		 String[] words = line.split("\\s");
		 
		 int[] position = new int[2]; //mang luu hai vi tri [bat dau, ket thuc] cua 1 string
		 
		 for(int i = 0;i< words.length;i++)
		 {
			 //System.out.println(words[i] + " " + numLine);
			  //xu ly truong hop: cac so, cac chu khong co di lien voi ky tu dac biet
			 if(words[i].matches("\\w+") == true)
			 {
				 //truong hop: la so
				 //can phai kiem tra no la so nguyen hay so thuc
				 if(words[i].matches("\\d") == true)
				 {
					 if(words[i].matches("[+-]?\\d+") == true)
					 {
						 position = indexOf(words[i], line);
						 System.out.println(words[i]+" ICONSTnumber "+ numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
						 
					 }
					 
					 if(words[i].matches("[+-]?\\d+\\.{1}\\d*") == true) 
					 {
						 position = indexOf(words[i], line);
						 System.out.println(words[i]+" RCONSTnumber "+ numLine +" "+ numLine + " " +position[0]+ " " + position[1]); 
					 }
					 
					 if(words[i].matches(DOUBLE_Pattern) == true)
					 {
						 position = indexOf(words[i], line);
						 System.out.println(words[i]+" DCONSTnumber "+ numLine +" "+ numLine + " " +position[0]+ " " + position[1]); 
					 }
				 }				 
				 else
				 {
					 //truong hop: la chu
					 // can phai kiem tra la keywords, hay identifier
					 if(words[i].matches("\\s+") == true)
					 {
						 continue;
					 }
					
					 position = indexOf(words[i], line);
				     System.out.println(words[i]+" words " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					 
				 }	  
			 }
			 else
			 {
				 //xu ly truong hop
				 //truong hop a++, a+b, (a == 5), -a, a;
				 //va truong hop la ky tu dac biet
				 if(words[i].matches("[+,;:=!\\\\(\\)><\\.-]+") == true)
				 {
					 switch(words[i])
					 {
					    case ";":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" SEMInumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case ":":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" COLONnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case ",":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" COMMAnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case ".":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" DOTnumber" + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case "(":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" LPARENnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case ")":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" RPARENnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case "<":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" LTnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case ">":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" GTnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case "=":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" EQnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case "-":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" MINUSnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case "+":
					    {
					    	position = indexOf_2(words[i], line);
					    	System.out.println(words[i]+" PLUSnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case "*":
					    {
					    	position = indexOf_2(words[i], line);
					    	System.out.println(words[i]+" TIMESnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    	
					    case "..":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" DOTDOTnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case ":=":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" COLEQnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case "<=":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" LEnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case ">=":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" GEnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					    case "<>":
					    {
					    	position = indexOf(words[i], line);
					    	System.out.println(words[i]+" NEnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
					    	break;
					    }
					 }
				 }
				 else
				 { 
					 //System.out.println(words[i]+" XXX " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
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
						   int demDau = 0;// kiem soat viec phan tich cac dau <= >= <> := ..   
						   
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
							    	   position = indexOf(b.toString(), line);
							    	   System.out.println(b.toString()+" words " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
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
									   position = indexOf(String.valueOf(v), line);
									   System.out.println(String.valueOf(v)+" ICONSTnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
									   peek = ' ';
								   }
								   else
								   {
									   //xet phan sau dau . (phan so thuc)
									   float x = v, d = 10;
									   m++;
					                   peek = list[m];
					                   while(Character.isDigit(peek) == true)
					                   {
					                	    demSO++;
					                	    x = x + Character.getNumericValue(peek) / d;
					                	    d = d * 10;
					                	    m++;
					                	    peek = list[m];
					                   }
					                   
					                   if(peek != 'e' || peek != 'E')
					                   {
					                	   position = indexOf(String.valueOf(x), line);
					                	   System.out.println(String.valueOf(x) + " RCONSTnumber " + numLine +" " +numLine + " " +position[0]+ " " + position[1]);
					                	   peek = ' ';
					                   }
					                   else
					                   {
					                	   String x_string = String.valueOf(x) + peek;
					                	   m++;
					                	   peek = list[m];
					                	   switch(peek)
					                	   {
					                	       case '+':
					                	       {
					                	    	   peek = list[m+1];
					                	    	   v = 0;
					                	    	   while(Character.isDigit(peek) == true)
					                	    	   {
					                	    		   demSO++; // bien nay de kiem soat viec dem phan tich cung 1 so, vd 34111
													   v = 10 * v + Character.getNumericValue(peek);
													   m++;
													   peek = list[m];
					                	    	   }
					                	    	   
					                	    	   x_string = x_string + "-" +String.valueOf(v);
					                	    	   break;
					                	       }
					                	       case '-':
					                	       {
					                	    	   peek = list[m+1];
					                	    	   v = 0;
					                	    	   while(Character.isDigit(peek) == true)
					                	    	   {
					                	    		   demSO++; // bien nay de kiem soat viec dem phan tich cung 1 so, vd 34111
													   v = 10 * v + Character.getNumericValue(peek);
													   m++;
													   peek = list[m];
					                	    	   }
					                	    	   
					                	    	   x_string = x_string + "+" +String.valueOf(v);
					                	    	   break;
					                	       }
					                	       
					                	       default:
					                	       {   
						                	    	   peek = list[m+1];
						                	    	   v = 0;
						                	    	   while(Character.isDigit(peek) == true)
						                	    	   {
						                	    		   demSO++; // bien nay de kiem soat viec dem phan tich cung 1 so, vd 34111
														   v = 10 * v + Character.getNumericValue(peek);
														   m++;
														   peek = list[m];
						                	    	   }
						                	    	   x_string = x_string + String.valueOf(v);
					                	       }
					                	   }
					                	   
					                	   position = indexOf(x_string, line);
					                	   System.out.println(x_string + " DCONSTnumber "+ numLine +" "+numLine+" " +position[0]+ " " + position[1]);
					                	   peek = ' ';
					                   }
								   }
							   }
							   
							   //kytudacbiet
							   
							   if(demDau > 0)
							   {
								   peek = ' ';
							   }
							   
							   switch(peek)
								 {
								    case ';':
								    {
								    	position = indexOf(peek, line);
								    	System.out.println(peek+" SEMInumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	break;
								    }
								    
								    case ':':
								    {
								    	if(list[j+1] == '=')
								    	{
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" COLEQnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    		demDau++;
								    	}
								    	else
								    	{
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" COLONnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	}
								    	
								    	break;
								    }
								    
								    case ',':
								    {
								    	position = indexOf(peek, line);
								    	System.out.println(peek+" COMMAnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	break;
								    }
								    
								    case '.':
								    {
								    	if(list[j+1] == '.')
								    	{
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" DOTDOTnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    		demDau++;
								    	}
								    	else
								    	{
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" DOTnumber" + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	}
								    	
								    	break;
								    }
								    
								    case '(':
								    {
								    	position = indexOf(peek, line);
								    	System.out.println(peek+" LPARENnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	break;
								    }
								    
								    case ')':
								    {
								    	position = indexOf(peek, line);
								    	System.out.println(peek+" RPARENnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	break;
								    }
								    
								    case '<':
								    {
								    	if(list[j+1] == '=')
								    	{
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" LEnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    		demDau++;
								    	}
								    	else if(list[j+1] == '>')
								    	{
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" NEnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    		demDau++;
								    	}
								    	else
								    	{
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" LTnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	}
								    	
								    	break;
								    }
								    
								    case '>':
								    {
								    	if(list[j+1] == '=')
								    	{
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" GEnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    		demDau++;
								    	}
								    	else
								    	{
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" GTnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	}
								    	
								    	break;
								    }
								    
								    case '=':
								    {
								    	position = indexOf(peek, line);
								    	System.out.println(peek+" EQnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	break;
								    }
								    
								    case '-':
								    {
								    	position = indexOf(peek, line);
								    	System.out.println(peek+" MINUSnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
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
								    		position = indexOf(peek, line);
								    		System.out.println(peek+" PLUSnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	}
								    	
								    	break;
								    }
								    
								    case '*':
								    {
								    	position = indexOf(peek, line);
								    	System.out.println(peek+" TIMESnumber " + numLine +" "+ numLine + " " +position[0]+ " " + position[1]);
								    	break;
								    }

								 }
							   
						   }
					 }
				 }
			 }		 
		 
	}	

	//ham xac dinh vi tri bat dau va ket thuc cua 1 String
	//xet cho cac truong + * ?, vv
    private int[] indexOf_2(String word, String line)
    {
    	System.out.println(line);
    	int[] pos = new int[2];
    	char[] lineArr = line.toCharArray(); //du lieu cua dong hien tai trong file
    	char[] wordArr = word.toCharArray(); //String dang xet
    	int start= 0, end = 0;
    	
    		for(int j = 0;j< lineArr.length;j++)
    		{
    			if(wordArr[0] == lineArr[j])
    			{
    				start = j+1;
    				break;
    			}
    		}
    		
    		for(int j = lineArr.length - 1;j >= 0;j--)
    		{
    			
    			if(wordArr[wordArr.length - 1] == lineArr[j])
    			{
    				end = j+1;
    				break;
    			}
    		}
    		//System.out.println("["+word + "]: start: " + start + ", end: " + end);
    		pos[0] = start;
    		pos[1] = end;
    		return pos;
    }
    
    //ham xac dinh vi tri bat dau va ket thuc cua 1 Char
    private int[] indexOf(char word, String line)
    {
    	int[] pos = new int[2];
    	
    	char[] lineArr = line.toCharArray(); //du lieu dong hien tai dang xet cua file
    	int start= 0, end = 0;
    	
    		for(int j = 0;j< lineArr.length;j++)
    		{
    			if(word == lineArr[j])
    			{
    				start = j+1;
    				break;
    			}
    		}
    		
    		for(int j = lineArr.length - 1;j >= 0;j--)
    		{
    			
    			if(word == lineArr[j])
    			{
    				end = j+1;
    				break;
    			}
    		}
    	//System.out.println("["+word + "]: start: " + start + ", end: " + end);
    	pos[0] = start;
    	pos[1] = end;
    	
    	return pos;
    	
    }


  //ham xac dinh vi tri bat dau va ket thuc cua 1 String
    private int[] indexOf(String word, String line)
    {
    	int[] pos = new int[2];
    	int start = 0, end = 0;
    	
    	if(word.length() == 1)
    	{
    	   String[] tmp = line.split("\\s");
    	   for(int i = 0;i<tmp.length;i++)
    	   {
    		   if(word == tmp[i])
    		   {
    			   start  = 0;
    			   end = 0;
    		   }
    	   }
    	   pos[0] = start;
    	   pos[1] = end;
    	}
    	else
    	{
    		Pattern patern = Pattern.compile(word);
   		    Matcher result = patern.matcher(line);
   		   // int i = 0;
   		    while(result.find()) {
   			 
   				 // i++;
   				  start = result.start();
   				  end = result.end();
   				 // System.out.println("Match "+i+" | Start: " + result.start() + " - End: " + result.end());	 
   			 
   		    }
	   		 pos[0] = start+1;
			 pos[1] = end;
    		
    	}
    	
    	return pos;
    	
    }

}


