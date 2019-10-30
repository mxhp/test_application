package com.xhp.testutils.bean;

import java.util.List;

public class Adv {

    /**
     * advItem : [{"advContentKey":"3#206#0101#&&9^^^^^^^^URL15319870660922769^","advId":"01200100","advSubType":"001","advURL":"VOD://HSGG9920180712001062","assetType":3,"backgroudColor":"","channelIds":"","contentId":"HSGG9920180712001062","contentName":"回看片头广告","displayTimes":-1,"duration":15,"interval":-1,"mD5":"34caeeea7805ad1ae1e03dac30cebb15","offset":-1,"offsetType":0,"skip":0,"videoStyle":0}]
     * checkInterval : 0
     * resultCode : 000000
     * resultCount : 1
     * resultDesc : success
     * sessionId : 1570966195528-22957-1228094560
     */

    private int checkInterval;
    private String resultCode;
    private int resultCount;
    private String resultDesc;
    private String sessionId;
    private List<AdvItemBean> advItem;

    public int getCheckInterval() {
        return checkInterval;
    }

    @Override
    public String toString() {
        return "Adv{" +
                "checkInterval=" + checkInterval +
                ", resultCode='" + resultCode + '\'' +
                ", resultCount=" + resultCount +
                ", resultDesc='" + resultDesc + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", advItem=" + advItem +
                '}';
    }

    public void setCheckInterval(int checkInterval) {
        this.checkInterval = checkInterval;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<AdvItemBean> getAdvItem() {
        return advItem;
    }

    public void setAdvItem(List<AdvItemBean> advItem) {
        this.advItem = advItem;
    }

    public static class AdvItemBean {
        /**
         * advContentKey : 3#206#0101#&&9^^^^^^^^URL15319870660922769^
         * advId : 01200100
         * advSubType : 001
         * advURL : VOD://HSGG9920180712001062
         * assetType : 3
         * backgroudColor :
         * channelIds :
         * contentId : HSGG9920180712001062
         * contentName : 回看片头广告
         * displayTimes : -1
         * duration : 15
         * interval : -1
         * mD5 : 34caeeea7805ad1ae1e03dac30cebb15
         * offset : -1
         * offsetType : 0
         * skip : 0
         * videoStyle : 0
         */

        private String advContentKey;
        private String advId;
        private String advSubType;
        private String advURL;
        private int assetType;
        private String backgroudColor;
        private String channelIds;
        private String contentId;
        private int displayTimes;
        private int duration;
        private int interval;
        private String mD5;
        private int offset;
        private int offsetType;
        private int skip;
        private int videoStyle;

        @Override
        public String toString() {
            return "AdvItemBean{" +
                    "advContentKey='" + advContentKey + '\'' +
                    ", advId='" + advId + '\'' +
                    ", advSubType='" + advSubType + '\'' +
                    ", advURL='" + advURL + '\'' +
                    ", assetType=" + assetType +
                    ", backgroudColor='" + backgroudColor + '\'' +
                    ", channelIds='" + channelIds + '\'' +
                    ", contentId='" + contentId + '\'' +
                    ", displayTimes=" + displayTimes +
                    ", duration=" + duration +
                    ", interval=" + interval +
                    ", mD5='" + mD5 + '\'' +
                    ", offset=" + offset +
                    ", offsetType=" + offsetType +
                    ", skip=" + skip +
                    ", videoStyle=" + videoStyle +
                    '}';
        }

        public String getAdvContentKey() {
            return advContentKey;
        }

        public void setAdvContentKey(String advContentKey) {
            this.advContentKey = advContentKey;
        }

        public String getAdvId() {
            return advId;
        }

        public void setAdvId(String advId) {
            this.advId = advId;
        }

        public String getAdvSubType() {
            return advSubType;
        }

        public void setAdvSubType(String advSubType) {
            this.advSubType = advSubType;
        }

        public String getAdvURL() {
            return advURL;
        }

        public void setAdvURL(String advURL) {
            this.advURL = advURL;
        }

        public int getAssetType() {
            return assetType;
        }

        public void setAssetType(int assetType) {
            this.assetType = assetType;
        }

        public String getBackgroudColor() {
            return backgroudColor;
        }

        public void setBackgroudColor(String backgroudColor) {
            this.backgroudColor = backgroudColor;
        }

        public String getChannelIds() {
            return channelIds;
        }

        public void setChannelIds(String channelIds) {
            this.channelIds = channelIds;
        }

        public String getContentId() {
            return contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

//        public String getContentName() {
//            return contentName;
//        }
//
//        public void setContentName(String contentName) {
//            this.contentName = contentName;
//        }

        public int getDisplayTimes() {
            return displayTimes;
        }

        public void setDisplayTimes(int displayTimes) {
            this.displayTimes = displayTimes;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getInterval() {
            return interval;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public String getMD5() {
            return mD5;
        }

        public void setMD5(String mD5) {
            this.mD5 = mD5;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getOffsetType() {
            return offsetType;
        }

        public void setOffsetType(int offsetType) {
            this.offsetType = offsetType;
        }

        public int getSkip() {
            return skip;
        }

        public void setSkip(int skip) {
            this.skip = skip;
        }

        public int getVideoStyle() {
            return videoStyle;
        }

        public void setVideoStyle(int videoStyle) {
            this.videoStyle = videoStyle;
        }
    }
}
