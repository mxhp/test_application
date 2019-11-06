package com.xhp.testutils.bean;

import java.util.List;
import java.util.Objects;

public class Category {
    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean implements Cloneable{

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public List<String> images;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ResultsBean)) return false;
            ResultsBean that = (ResultsBean) o;
            return Objects.equals(_id, that._id) &&
                    Objects.equals(publishedAt, that.publishedAt) &&
                    Objects.equals(url, that.url) &&
                    Objects.equals(who, that.who) &&
                    Objects.equals(images, that.images);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_id, publishedAt, url, who, images);
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            ResultsBean bean = null;
            try {
                bean = (ResultsBean) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return bean;

        }
    }
}
