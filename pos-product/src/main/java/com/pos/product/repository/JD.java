package com.pos.product.repository;

import com.pos.product.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JD implements ProductRepository {


    private List<Product> products = null;

    @Override
    public List<Product> allProducts() {
        try {
            if (products == null)
                products = parseJD("Java");
        } catch (IOException e) {
            products = new ArrayList<>();
        }
        return products;
    }

    @Override
    public Product findProduct(String productId) {
        for (Product p : allProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> parseJD(String keyword) throws IOException {
            String cookie = "shshshfpa=e7d31132-3bd0-b0a6-2895-1a2a079adedd-1681821407; shshshfpx=e7d31132-3bd0-b0a6-2895-1a2a079adedd-1681821407; __jdu=1681821362795592738217; TrackID=1y875YB-P-xCqePUECw6ruHyMuswEi8mfOdwwqHYqFZFH8fyk9E71udp4T_yVX-kvlF79eBSaOLUVg_lRejgPgfdLt2cgXUeqsCZfnGy-a40; pinId=nvnosFd2OltEP0c9oIBvZA; user-key=63d9a4aa-3a1a-4c34-815c-a45a74c6abb9; unpl=JF8EAJ5nNSttWBsAUE8BSUBAHw9VW1sNGUcHaDINAwkNSwQDHAEfE0N7XlVdWBRKEh9tZhRXX1NIUg4ZBSsSEHteVVxcDEsRA21uNWRVUCVXSBtsGHwQBhAZbl4IexcCX2cCXV1RSFQHHAEdGhRPVVdYXwFIFwVfZjVUW2h7ZAQrAysTIAAzVRNdD0IXCmxnB1NeXkNQARMBHRAZSF1Sblw4SA; __jdv=95931165|direct|-|none|-|1715408686994; wlfstk_smdl=f1rd3dxckvzx3victij27pksuvzdz2tf; 3AB9D23F7A4B3CSS=jdd03UZEEUHRZEBINBKULW7SLLKPYKF54FRQMENC2RTM2VSNRANNK4GIFVOJFRR6JUTBRU44DHEO3WJBKI2HIOUCZDQWFGIAAAAMPV7OSZSAAAAAADNQIYMF2XD3C4UX; _gia_d=1; _pst=jd_DJQkUpaNbBxc; logintype=wx; unick=jd_DJQkUpaNbBxc; pin=jd_DJQkUpaNbBxc; npin=jd_DJQkUpaNbBxc; thor=E366CB4EAEDF804EE9205351B9E848279C0E0A8A5C486079499821920F26C6E82D253A3025B60D4F2008A6A8A5BAD256DD9C37EC0897DBD181C3D9FB1347D08133CF7C7526FF7A8712AFBB4A9B51F92B104E9C0EDA43AF78DC37A03E5D379A50AACBC23A3B414D28AD2665134E79CFA0BCEC0ABF92AEF78EA747831B45B41099A1558CD3AC8FD9A127979CACCEE8B1BF897538DBCA2774BBEEF7C1F0A361011B; flash=2_KopJTzhlU3k_AsdmO6U1k2dHwf0jSg84bnfsQE9hcq_LL2NmruECi3F8rwYOEtZnIlFjsk_h1ha_pr0gyd8U9KtjaUv1DM-tUSXoCersuZW8o9N9VWjMBlWFue7sscmtUjj0AWrjDPe3R71F7MVt81m9z0iEtJdBaOb-QlMzu65*; _tp=QvQWFz8c3CugfwdOb%2BM2yQ%3D%3D; ipLoc-djd=12-904-0-0; jsavif=1; __jda=143920055.1681821362795592738217.1681821362.1715408687.1716642459.16; __jdc=143920055; areaId=12; __jdb=143920055.4.1681821362795592738217|16.1716642459; shshshfpb=BApXcOyrVrOpAJb8iyZdeEppcc128XG8HB8PDEyhu9xJ1MufEvoO2; 3AB9D23F7A4B3C9B=UZEEUHRZEBINBKULW7SLLKPYKF54FRQMENC2RTM2VSNRANNK4GIFVOJFRR6JUTBRU44DHEO3WJBKI2HIOUCZDQWFGI; __jdb=143920055.3.1681821362795592738217|14.1714490857";
        //获取请求https://search.jd.com/Search?keyword=java
            String url = "https://search.jd.com/Search?keyword=" + keyword;
            //解析网页
            URI uri = URI.create(url);
            URL urlObj = uri.toURL();

            Connection connection = Jsoup.connect(urlObj.toString()).header("cookie", cookie).timeout(10000);

            Document document = connection.get();
            //所有js的方法都能用
            Element element = document.getElementById("J_goodsList");
            //获取所有li标签
            Elements elements = element.getElementsByTag("li");
        //        System.out.println(element.html());
            List<Product> list = new ArrayList<>();

        //获取元素的内容
        for (Element el : elements) {
            //关于图片特别多的网站，所有图片都是延迟加载的
            String id = el.attr("data-spu");
            if (id == null || id.length() == 0)
                continue;
            String img = "https:".concat(el.getElementsByTag("img").eq(0).attr("data-lazy-img"));
            String price = el.getElementsByAttribute("data-price").text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            if (title.indexOf("，") >= 0)
                title = title.substring(0, title.indexOf("，"));

            Product product = new Product(id, title, Double.parseDouble(price), img, 1, 10);

            list.add(product);
        }
        return list;
    }

}
