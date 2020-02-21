package com.knowin.city.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity(
        indexes = {
            @Index(value = "city DESC", unique = true)
        },
        createInDb = true
)
public class IntlCity {
    @Id(autoincrement = true)
    private Long id;  //递增ID

    private Long mojiId; //墨迹城市ID

    @NotNull
    private String city;
    private String english_name; //英文名字

@Generated(hash = 814585546)
public IntlCity(Long id, Long mojiId, @NotNull String city,
        String english_name) {
    this.id = id;
    this.mojiId = mojiId;
    this.city = city;
    this.english_name = english_name;
}
@Generated(hash = 938860959)
public IntlCity() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
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
public Long getMojiId() {
    return this.mojiId;
}
public void setMojiId(Long mojiId) {
    this.mojiId = mojiId;
}
}
