package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {

    public static void main(String[] args) {
    
        System.out.println("PROGRAM STARTED");
       // String url ="https://webscraper.io/test-sites/e-commerce/allinone/phones/touch";
       // String url ="https://webscraper.io/test-sites/e-commerce/allinone/computers/laptops";
          String url ="https://webscraper.io/test-sites/e-commerce/allinone/computers/tablets";
        
        try {
            System.out.println("--------------------------------");

            Document document = Jsoup.connect(url).get();

            System.out.println("Fetched data from URL---->");

            Elements products = document.select("div.thumbnail");

            System.out.println("Total Products: " + products.size());
            System.out.println("--------------------------------");

            for (Element product : products) {

                String name = product.select("a.title").text();
                String price = product.select("h4.price").text();
                
                //stars
                int stars = product.select(
                        "div.ratings span.glyphicon.glyphicon-star, " +
                        "div.ratings span.glyphicon-star"
                ).size();

                if (stars == 0) {
                    Element ratingElement = product.selectFirst("div.ratings [data-rating]");
                    if (ratingElement != null) {
                        stars = Integer.parseInt(ratingElement.attr("data-rating").trim());
                    }
                }
                
                //reviews
                int reviews = 0;
                Element reviewElement = product.selectFirst("span[itemprop=reviewCount]");
                if (reviewElement != null) {
                    reviews = Integer.parseInt(reviewElement.text().trim());
                }

                System.out.println("Product Name: " + name);
                System.out.println("Price: " + price);
                System.out.println("Star rating: " + stars);
                System.out.println("Number of Reviews: " + reviews);
                System.out.println("--------------------------------");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
