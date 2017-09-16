

public  class XmlParse{


    public String parse(String xml,String tab){
        String s="";
        int startIndex=-1;
        int endIndex=-1;

         startIndex=xml.indexOf( "<"+tab+">");
         endIndex=xml.indexOf("</"+tab+">");;
        //System.out.println(xml);
        //System.out.println(startIndex);
        //System.out.println(endIndex);

        if(startIndex!=-1){
            int contentStart=xml.indexOf('>',startIndex)+1;
            s= xml.substring(contentStart,endIndex);
        }
        return s;
    }
}
