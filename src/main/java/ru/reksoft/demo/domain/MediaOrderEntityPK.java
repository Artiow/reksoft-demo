package ru.reksoft.demo.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MediaOrderEntityPK implements Serializable {

    private Integer mediaId;
    private Integer orderId;


    @Id
    @Column(name = "media_id", nullable = false)
    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    @Id
    @Column(name = "order_id", nullable = false)
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaOrderEntityPK that = (MediaOrderEntityPK) o;

        if (!mediaId.equals(that.mediaId)) return false;
        return orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        int result = mediaId.hashCode();
        result = 31 * result + orderId.hashCode();
        return result;
    }
}
