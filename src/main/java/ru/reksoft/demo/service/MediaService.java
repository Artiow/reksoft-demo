package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.repository.MediaRepository;
import ru.reksoft.demo.service.util.MediaFilter;
import ru.reksoft.demo.service.util.PageDivider;

import javax.validation.constraints.NotNull;

@Service
public class MediaService {

    private MediaRepository mediaRepository;

    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }


    /**
     * Returns page with filtered media
     *
     * @param filterDTO - filter for media
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaList(@NotNull MediaFilterDTO filterDTO) {
        MediaFilter filter = new MediaFilter(filterDTO);
        return new PageDTO<>(mediaRepository.findAll(filter, filter.getPageRequest()).map(MediaShortDTO::new));
    }


    /**
     * Returns page with media by album by id
     *
     * @param id - album id
     * @param pdDTO - page divider
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListBySinger(@NotNull Integer id, @NotNull PageDividerDTO pdDTO) {
        PageDivider pd = new PageDivider(pdDTO);
        return new PageDTO<>(mediaRepository.findByAlbum_Singer_Id(id, pd.getPageRequest()).map(MediaShortDTO::new));
    }

    /**
     * Returns page with media by label by id
     *
     * @param id - label id
     * @param pdDTO - page divider
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListByLabel(@NotNull Integer id, @NotNull PageDividerDTO pdDTO) {
        PageDivider pd = new PageDivider(pdDTO);
        return new PageDTO<>(mediaRepository.findByAlbum_Label_Id(id, pd.getPageRequest()).map(MediaShortDTO::new));
    }

    /**
     * Returns page with media by album by id
     *
     * @param id - album id
     * @param pdDTO - page divider
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListByAlbum(@NotNull Integer id, @NotNull PageDividerDTO pdDTO) {
        PageDivider pd = new PageDivider(pdDTO);
        return new PageDTO<>(mediaRepository.findByAlbum_Id(id, pd.getPageRequest()).map(MediaShortDTO::new));
    }


    /**
     * Returns media by id
     *
     * @return media
     */
    @Transactional(readOnly = true)
    public MediaDTO getMedia(Integer id) {
        return new MediaDTO(mediaRepository.getOne(id));
    }
}
