package com.github.hanyaeger.api.scenes;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.sprite.SpriteEntity;

class SpriteEntityThree extends SpriteEntity {

    public SpriteEntityThree(Coordinate2D location, Size size) {
        super(TileMapTest.DEFAULT_RESOURCE, location, size);
    }
}
