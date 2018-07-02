package ru.reksoft.demo.mapper.generic;

import ru.reksoft.demo.domain.generic.AbstractVersionedEntity;
import ru.reksoft.demo.dto.generic.AbstractVersionedDTO;

public interface AbstractVersionedMapper<E extends AbstractVersionedEntity, D extends AbstractVersionedDTO> extends AbstractMapper<E, D> {

    default E merge(E acceptor, E donor) {
        acceptor.setVersion(donor.getVersion());

        return acceptor;
    }
}
