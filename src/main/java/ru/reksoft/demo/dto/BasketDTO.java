package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.DataTransferObject;
import ru.reksoft.demo.dto.shortcut.MediaShortDTO;

import java.util.List;

public class BasketDTO implements DataTransferObject {

    private Integer numberOfElements;
    private List<BasketItemDTO> content;


    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public BasketDTO setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    public List<BasketItemDTO> getContent() {
        return content;
    }

    public BasketDTO setContent(List<BasketItemDTO> content) {
        this.content = content;
        return this;
    }


    public static class BasketItemDTO implements DataTransferObject {

        private MediaShortDTO media;
        private Integer amount;


        public MediaShortDTO getMedia() {
            return media;
        }

        public BasketItemDTO setMedia(MediaShortDTO media) {
            this.media = media;
            return this;
        }

        public Integer getAmount() {
            return amount;
        }

        public BasketItemDTO setAmount(Integer amount) {
            this.amount = amount;
            return this;
        }
    }
}
