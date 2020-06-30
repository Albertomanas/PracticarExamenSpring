package org.elsmancs.practica.dominio;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;


@Entity
@DiscriminatorValue("NormalItem")
public class NormalItem extends Item {

    public int getQuality() {
        return this.getPropiedad();
    }
    
}