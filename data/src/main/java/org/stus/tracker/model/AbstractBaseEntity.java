package org.stus.tracker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public abstract class AbstractBaseEntity {

    protected Long id;

}
