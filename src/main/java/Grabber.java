import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grabber{

    //Grab Url From GitHub
    //Grab Name From GitHub

    public String getSoup(String url){
        String content="";
        try {
            Document doc=Jsoup.connect(url)
                    .data("jquery", "java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(50000)
                    .get();
            content=doc.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public String getDivContentByJsoup(String content){
        String divContent="";
        Document doc= Jsoup.parse(content);
        Elements divs=doc.getElementsByClass("js-timeline-item js-timeline-progressive-focus-container");
        divContent=divs.toString();
        return divContent;
    }

    public List<String> getLinks(){
        String abs="https://github.com/STIW3054-A191/Assignments/issues/1";
        String HtmlContent= getSoup(abs);
        String divContent=getDivContentByJsoup(HtmlContent);
        Document doc1=Jsoup.parse(divContent,abs);
        Elements linkStrs=doc1.getElementsByClass("d-block comment-body markdown-body  js-comment-body");
        List<String> urlList=new ArrayList<String>();
        for(Element linkStr:linkStrs){
            String url=linkStr.getElementsByTag("a").attr("abs:href");
            //System.out.println("url:"+url);
            urlList.add(url);
        }
        Set<String> set = new HashSet<>(urlList);
        urlList.clear();
        urlList.addAll(set);
        return urlList;
    }


}
