package ru.reksoft.demo.domain.generic;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractVersionedEntity extends AbstractIdentifiedEntity {

    @Version
    @Column(name = "version")
    private Long version = 1L;


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
