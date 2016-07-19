package com.example.anull.excitingnews.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

/**
 * Created by null on 2016/7/18.
 */
public class NewsList {
    /**
     * date : 20160718
     * stories : [{"title":"住在普通居民楼的我们，其实每天都被这五层防线保护着","ga_prefix":"071813","images":["http://pic4.zhimg.com/f2eb204ae3ca6dafc3882a4b75162777.jpg"],"multipic":true,"type":0,"id":8578759},{"images":["http://pic4.zhimg.com/5407696897709b38a790833b70d3c4b3.jpg"],"type":0,"id":8579024,"ga_prefix":"071812","title":"大误 · 啊啊啊身体缩小了"},{"images":["http://pic1.zhimg.com/1e110ffbd47021f66526fedb93ab3408.jpg"],"type":0,"id":8562941,"ga_prefix":"071811","title":"一边讲出情话，一边痛哭泪流，言情剧必杀技你也可以感受"},{"title":"曾经有人为一张票等待十年，欧洲音乐节究竟有多么「梦寐以求」","ga_prefix":"071810","images":["http://pic1.zhimg.com/24aa5fa09c07a16b70bd9e42ce338574.jpg"],"multipic":true,"type":0,"id":8577498},{"images":["http://pic4.zhimg.com/aa0c69e3a8b9c9e4d769b82d1818a683.jpg"],"type":0,"id":8564712,"ga_prefix":"071809","title":"天天吃白菜以为要瘦了，没想到体重计还是没放过我"},{"images":["http://pic1.zhimg.com/9be47c8a45f7f844053fcafee3184c7c.jpg"],"type":0,"id":8576918,"ga_prefix":"071808","title":"药物说明书「太长看不下去」，非常正常"},{"images":["http://pic3.zhimg.com/031e1d2a21de7e95cf36887fabfa37ce.jpg"],"type":0,"id":8577513,"ga_prefix":"071807","title":"Cosplay 亚里士多德，你只需要床单和一些别针"},{"images":["http://pic4.zhimg.com/a757a0ef2a2f433edada455f09e741df.jpg"],"type":0,"id":8576906,"ga_prefix":"071807","title":"从这个充满「扑克脸」的团体中，看到真实的自己"},{"images":["http://pic1.zhimg.com/4e8533cc117689d8a2fdac358ea36140.jpg"],"type":0,"id":8565504,"ga_prefix":"071807","title":"味精毁了中国的美食吗？"},{"images":["http://pic4.zhimg.com/5557ee18e405b203a5d06c79edc21cf7.jpg"],"type":0,"id":8577696,"ga_prefix":"071807","title":"读读日报 24 小时热门 TOP 5 · 最通俗的动车组科普文章"},{"images":["http://pic4.zhimg.com/5d1034e4ed4fda06e74fab8a0087909f.jpg"],"type":0,"id":8570079,"ga_prefix":"071806","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/51f2dc3d1dd672f58a1e134426e05392.jpg","type":0,"id":8577696,"ga_prefix":"071807","title":"读读日报 24 小时热门 TOP 5 · 最通俗的动车组科普文章"},{"image":"http://pic1.zhimg.com/b4cfcb5bcfd7b75e9f088b6e6f5e1350.jpg","type":0,"id":8565504,"ga_prefix":"071807","title":"味精毁了中国的美食吗？"},{"image":"http://pic1.zhimg.com/6987f89e1f680d12dceb1e58fed796ec.jpg","type":0,"id":8566245,"ga_prefix":"071721","title":"你敢和你对象一块儿玩这个作死的游戏吗？"},{"image":"http://pic3.zhimg.com/2116e3f90e3b079dd428d591a6935676.jpg","type":0,"id":8576092,"ga_prefix":"071717","title":"知乎好问题 · 有哪些像《纪念碑谷》这样接近艺术的游戏？"},{"image":"http://pic4.zhimg.com/d6d68761f6cbd534229b049eebc8f16f.jpg","type":0,"id":8574873,"ga_prefix":"071707","title":"读读日报 24 小时热门 TOP 5 · 成本 100 万的电影，帮助中国电影进步五十年"}]
     */

    private String date;
    /**
     * title : 住在普通居民楼的我们，其实每天都被这五层防线保护着
     * ga_prefix : 071813
     * images : ["http://pic4.zhimg.com/f2eb204ae3ca6dafc3882a4b75162777.jpg"]
     * multipic : true
     * type : 0
     * id : 8578759
     */

    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean implements Parcelable {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StoriesBean that = (StoriesBean) o;
            return id == that.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        private String title;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeInt(this.id);
            dest.writeStringList(this.images);
        }

        public StoriesBean() {
        }

        protected StoriesBean(Parcel in) {
            this.title = in.readString();
            this.id = in.readInt();
            this.images = in.createStringArrayList();
        }

        public static final Parcelable.Creator<StoriesBean> CREATOR = new Parcelable.Creator<StoriesBean>() {
            @Override
            public StoriesBean createFromParcel(Parcel source) {
                return new StoriesBean(source);
            }

            @Override
            public StoriesBean[] newArray(int size) {
                return new StoriesBean[size];
            }
        };
    }
}
