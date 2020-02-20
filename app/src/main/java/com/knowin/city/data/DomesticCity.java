package com.knowin.city.data;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity(
        indexes = {
            @Index(value = "province, city DESC", unique = true)
        },
        createInDb = true
)
public class DomesticCity {
    @Id(autoincrement = true)
    private Long id;  //递增ID

    private Long mojiId;  //墨迹天气的地域ID， 第2列的值,方便天气查询时使用

    private int province; //所属省/直辖市/特区

    private boolean isSpot; //是否是景点

    @NotNull
    private String city;
    private String english_name; //英文名字
@Generated(hash = 650330723)
public DomesticCity(Long id, Long mojiId, int province, boolean isSpot,
        @NotNull String city, String english_name) {
    this.id = id;
    this.mojiId = mojiId;
    this.province = province;
    this.isSpot = isSpot;
    this.city = city;
    this.english_name = english_name;
}
@Generated(hash = 1891326997)
public DomesticCity() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public Long getMojiId() {
    return this.mojiId;
}
public void setMojiId(Long mojiId) {
    this.mojiId = mojiId;
}
public int getProvince() {
    return this.province;
}
public void setProvince(int province) {
    this.province = province;
}
public boolean getIsSpot() {
    return this.isSpot;
}
public void setIsSpot(boolean isSpot) {
    this.isSpot = isSpot;
}
public String getCity() {
    return this.city;
}
public void setCity(String city) {
    this.city = city;
}
public String getEnglish_name() {
    return this.english_name;
}
public void setEnglish_name(String english_name) {
    this.english_name = english_name;
}
}
