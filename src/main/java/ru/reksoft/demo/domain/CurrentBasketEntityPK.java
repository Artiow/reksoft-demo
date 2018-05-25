package ru.reksoft.demo.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CurrentBasketEntityPK implements Serializable {

    private Integer userId;
    private Integer mediaId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentBasketEntityPK that = (CurrentBasketEntityPK) o;

        if (!userId.equals(that.userId)) return false;
        return mediaId.equals(that.mediaId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + mediaId.hashCode();
        return result;
    }
}
