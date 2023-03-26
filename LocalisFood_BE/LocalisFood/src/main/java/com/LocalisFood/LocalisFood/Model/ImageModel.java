package com.LocalisFood.LocalisFood.Model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Entity
    @Table(name = "image_table")
    public class ImageModel {

        public ImageModel() {
            super();
        }

        public ImageModel(String name, String type, byte[] picByte) {
            this.name = name;
            this.type = type;
            this.picByte = picByte;
        }

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "type")
        private String type;

        @NotNull
        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "productId", referencedColumnName = "productId")
        private Product product;

        //image bytes can have large lengths so we specify a value
        //which is more than the default length for picByte column
        @Lob
        @Column(name = "picByte", length = Integer.MAX_VALUE)
        private byte[] picByte;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public byte[] getPicByte() {
            return picByte;
        }

        public void setPicByte(byte[] picByte) {
            this.picByte = picByte;
        }
    }

