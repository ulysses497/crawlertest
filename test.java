public class AnnotationCs  implements AfterExtractor {
    @ExtractBy("//div[@id=\"content\"]")
    private String content;
    @ExtractByUrl(value = "(?<=book_112633/)\\d*")
    private String urlpageinfo;
    @ExtractBy(value = "//h1/text()",notNull = true)
    private String title;
    @ExtractBy("//div[@id='list']//dd/a/@href")
    private List<String> url;
    @ExtractByUrl(".*")
    private String danduurl;

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public String getDanduurl() {
        return danduurl;
    }

    public void setDanduurl(String danduurl) {
        this.danduurl = danduurl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlpageinfo() {
        return urlpageinfo;
    }

    public void setUrlpageinfo(String urlpageinfo) {
        this.urlpageinfo = urlpageinfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public void afterProcess(Page page) {
        if (url!=null){
            Iterator<String> iterator = url.iterator();
            int i = 0;
            while (iterator.hasNext()){
                String next = iterator.next();
                url.set(i,"https://www.52bqg.com/book_112633/"+next);
                i = i+1;
            }
            page.addTargetRequests(url);
        }
//        if (getDanduurl()=="https://www.52bqg.com/book_112633"){
//            Iterator<String> iterator = getUrl().iterator();
//            int i = 0;
//            while (iterator.hasNext()){
//                String next = iterator.next();
//                getUrl().set(i,"https://www.52bqg.com/book_112633/"+next);
//                i = i+1;
//            }
//            List<String> url2 = getUrl();
//
//            IXiaoshuoMapper mapper = Applicacs.getixiaoshuomapper();
//            System.out.println("查漏开始:"+url2.get(0));
//            Iterator<String> iterator1 = url2.iterator();
//            while (iterator1.hasNext()){
//                String urltoget = iterator1.next();
//                if (mapper.findurl(urltoget)==0){
//                    System.out.println("漏掉的url:"+urltoget);
//                }
//            }
//        }
    }
    public static void main(String[] args) throws IOException {

        OOSpider.create(Site.me().setSleepTime(1000)
                , new AnnotationCsPipline(), AnnotationCs.class)
                .addUrl("https://www.52bqg.com/book_112633")
                .thread(6).setScheduler(new QueueScheduler()
                .setDuplicateRemover(new BloomFilterDuplicateRemover(1000))).run();
    }


}
