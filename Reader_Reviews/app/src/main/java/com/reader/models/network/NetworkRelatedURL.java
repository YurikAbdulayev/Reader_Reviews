package com.reader.models.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yurik on 26.02.16.
 */
public class NetworkRelatedURL {


        private String type;

        private String url;

        @SerializedName("suggested_link_url")
        private String suggestedLinkText;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSuggestedLinkText() {
            return suggestedLinkText;
        }

        public void setSuggestedLinkText(String suggestedLinkText) {
            this.suggestedLinkText = suggestedLinkText;
        }

}
