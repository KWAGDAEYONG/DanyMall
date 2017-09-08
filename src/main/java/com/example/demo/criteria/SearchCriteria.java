package com.example.demo.criteria;

import com.example.demo.model.QItem;
import com.querydsl.core.BooleanBuilder;

public class SearchCriteria extends PageCriteria {
    private String price="";
    private String weather="";
    private String style="";
    private String gender="";
    private String category="";

    public void setPrice(String price) {
        this.price = price;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public String getWeather() {
        return weather;
    }

    public String getStyle() {
        return style;
    }

    public String getGender() {
        return gender;
    }

    public String getCategory() {
        return category;
    }

    public BooleanBuilder getWhere(){
        QItem qItem =QItem.item;
        BooleanBuilder br = new BooleanBuilder();
        if(!"".equals(this.price)&&this.price!=null) {
            this.price = this.price.trim();
            this.price = this.price.replace(" ", "");

            String between[] = this.price.split("-");
            between[0] = between[0].substring(1);
            between[1] = between[1].substring(1);
            br.and(qItem.price.between(Integer.parseInt(between[0]),Integer.parseInt(between[1])));
        }

        if(!"".equals(this.weather)&&this.weather!=null){
            br.and(qItem.weather.eq(this.weather));
        }

        if(!"".equals(this.style)&&this.style!=null){
            br.and(qItem.style.eq(this.style));
        }

        if(!"".equals(this.gender)&&this.gender!=null){
            br.and(qItem.gender.eq(this.gender));
        }

        if(!"".equals(this.category)&&this.category!=null){
            br.and(qItem.category.part.eq(this.category));
        }
        return br;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "price='" + price + '\'' +
                ", weather='" + weather + '\'' +
                ", style='" + style + '\'' +
                ", gender='" + gender + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
