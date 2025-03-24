package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthorityKind {
    
    ITEM_WATCHER("1"),
    
    ITEM_MANAGER("2"),

    ITEM_AND_USER_MANAGER("3");

    private String AuthorityKind;
}
