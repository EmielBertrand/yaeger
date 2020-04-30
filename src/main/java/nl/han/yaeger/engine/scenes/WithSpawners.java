package nl.han.yaeger.engine.scenes;

import nl.han.yaeger.engine.DependencyInjector;
import nl.han.yaeger.engine.Updatable;
import nl.han.yaeger.engine.annotations.OnPostActivation;
import nl.han.yaeger.engine.annotations.UpdatableProvider;
import nl.han.yaeger.engine.entities.EntitySpawner;
import nl.han.yaeger.engine.exceptions.YaegerEngineException;

/**
 * When implementing this interface, the {@link #setupEntitySpawners()} method needs to be implemented.
 * This interface can be used with a {@link YaegerScene} and ensures that the method {@link #setupEntitySpawners()}
 * is being called during initialization of such an object.
 * <p>
 * THe body of {@link #setupEntitySpawners()} should be used to add instances of {@link EntitySpawner}, using the exposed
 * method  {@link #addEntitySpawner(EntitySpawner)}. These spawners will then be registered and added to the Game-loop.
 * <p>
 * A {@link EntitySpawner} that is instantiated, but not added in this way, will not work.
 */
public interface WithSpawners extends EntitySpawnerListProvider, EntityCollectionSupplier, DependencyInjector {

    /**
     * Use this method to add any {@link EntitySpawner} that is required by the {@link YaegerScene}.
     *
     * @param entitySpawner The {@link EntitySpawner} that needs to be added.
     */
    default void addEntitySpawner(EntitySpawner entitySpawner) {
        if (getSpawners() != null) {
            getInjector().injectMembers(entitySpawner);
            getSpawners().add(entitySpawner);
        } else {
            throw new YaegerEngineException("getSpawners() returns null, please return an instance of ArrayList<>");
        }
    }

    @OnPostActivation
    default void initSpawners() {
        getSpawners().clear();
        setupEntitySpawners();
        registerEntitySpawners();
    }

    default void registerEntitySpawners() {
        getSpawners().forEach(spawner -> getEntityCollection().registerSupplier(spawner.getSupplier()));
    }

    /**
     * Only instances of {@link EntitySpawner} that are registered with the method {@link #addEntitySpawner(EntitySpawner)}
     * within this method are registered and will receive an animation update.
     */
    void setupEntitySpawners();

    @UpdatableProvider
    default Updatable callEntitySpawners() {
        return timestamp -> {
            if (getSpawners() != null && !getSpawners().isEmpty()) {
                getSpawners().forEach(entitySpawner -> entitySpawner.handle(timestamp));
            }
        };
    }
}