
/*XML格式化发送信息*/

public class XmlFormat{
    private String sendString;


    public XmlFormat(){

    }

    public String xmlForm(String  type,String content,String file,String ip,String port,String hostname,String face,String email){

        sendString="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+
                             "<msg>"                      +
                                 "<type>"                 +
                                         type              +
                                 "</type>"                +
                                 
                                 "<content>"              +
                                         content            +
                                  "</content>"            +
                                  
                                  "<file>"              +
                                         file            +
                                  "</file>"            +
                                  
                                  "<ip>"                   +
                                         ip                 +
                                  "</ip>"                  +
                                                           
                                  "<port>"                 +
                                          port               +
                                  "</port>"                +
                                  
                                  "<hostname>"                   +
                                           hostname                   +
                                  "</hostname>"                  +
                                  
                                  "<face>"                       +
                                           face                       +
                                  "</face>"                      +
                                  
                                  "<email>"                      +
                                           email                      +//好友身份验证工具
                                  "</email>"                     +                                           
                                
                             "</msg>";
                return sendString;

        }


}