package com.rawt.productrepo.model;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "images")
@Data
public class Image {
    @Id
    private String id;
    private Binary image;
}
