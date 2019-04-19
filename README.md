# Lexical_Analysis
- This project made by Trinh Hang Uoc and Nguyen Le Thanh Nhan

==Update 26/3/2019
-tao 1 class Lexcical voi 2 ham chinh
-    public void xuLyFile(String fileInput): xu ly doc tung dong trong file
-    private void scanLine(string Line, int numLine): xu ly tu vung tren tung dong

-hien trang: da doc duoc file, xu ly nhung phan co ban

- tach bang dau \\s, luu thanh 1 mang String xet tung chu trong mang
-  //chu ~ can phai phan ra no la Keywords hoac la IDnumber
-  //so ~ can phan phan biet no la so nguyen hoac so thuc
-  //chu_kyhieudacbiet (vd a; a, y; 3411;)
- tach thanh 1 mang chu cai
-     phan tich tung chu cai
-       //so ~ can phan phan biet no la so nguyen hoac so thuc
-       //chu ~ can phai phan ra no la Keywords hoac la IDnumber
-       //ky tu dac biet
-         +ky tu 1 ngoi (> < =)
-         +ky tu 2 ngoi (>= <= <> :=)
- code them phan kiem tra chuoi la so nguyen, so thuc (Done at 4:30 26/3/2019)


===PHASE 2:
### Grammer BNF notation (Self-Defined):
-program ::= 'program' programName; 'begin' Declaration statementList 'end.'

-Declaration ::= Type ID; | <mVar> | empty
  
-mVar ::= Type ID | empty

-statemetList ::= Statement | IfStatement | WhileStatement | Assign

-IFstatement ::= 'if' condEpression 'then' trueStatements ['else' falseStatement] 'endif'

-condEpression ::= Expression <RO> Epression
  
-trueStatement ::= statement | block

-elseStatement ::= statement | block

-block ::=  'begin' statementList 'end'

-WhileStatement ::= 'while' condEpression 'loop' block 'endloop'

-Assign ::= ID := Epxression

-Epression ::= <T> * Expression | <T> / Expression | <T>
  
-<T> ::= <F> + <T> | <F> - <T> | <F>
  
-<F> ::= -<F> | <R>
  
-<R> ::= (Expression) | ID | NUM
  
-NUM ::= ICONST | FLOATCONST | CHARCONST

-<RO> ::= =< | >= | == | > | < | <>

== can ban da test dung voi cac Testcase co san

