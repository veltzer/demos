package coreservlets.client;

import com.google.gwt.user.client.ui.*;

public class ProgrammingLanguageOracle 
       extends MultiWordSuggestOracle {
  // 50 most popular programming languages, listed in order. 
  // From http://www.tiobe.com/tpci.htm
  private static final String languages = 
    "Java,C,C++,PHP,Visual Basic,Perl,Python,C#,JavaScript,Ruby,Delphi,SAS,SQL,"+
    "D,Lisp,Ada,FoxPro,Fortran,COBOL,Pascal,Lua,ActionScript,awk,Tcl/Tk,Prolog," +
    "MATLAB,Logo,IDL,Bash,ColdFusion,RPG,LabView,Smalltalk,Forth,ML,CL,Haskell," +
    "APL,VBScript,Natural,PL/I,Icon,Lingo,S-lang,Objective-C,OCaml,REXX,Mathematica";
  private static final String[] languageNames = languages.split(",");
  
  public ProgrammingLanguageOracle() {
    for(String language: languageNames) {
      add(language);
    }
  }
}
