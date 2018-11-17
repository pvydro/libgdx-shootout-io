package com.flizzet.containers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.entity.Entity;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Updatable;

/**
 * Container.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Container<E> implements Renderable, Updatable {

	private ArrayList<E> all = new ArrayList<E>();
	private Queue<E> toBeAdded = new LinkedList<E>();
	private Queue<E> toBeRemoved = new LinkedList<E>();

	/** Default instantiable constructor */
	public Container() {
	}

	@Override
	public void update(float delta) {
		all.removeAll(toBeRemoved);
		toBeRemoved.removeAll(toBeRemoved);
		all.addAll(toBeAdded);
		toBeAdded.removeAll(toBeAdded);

		for (E e : all) {
			if (e instanceof Updatable) {
				((Updatable) e).update(delta);
			}
		}
	}

	@Override public void render(SpriteBatch batch, ShapeRenderer sr) {
		for (E e : all) {
			if (e instanceof Renderable) {
				((Renderable) e).render(batch, sr);
			}
		}
	}
	
	/**
	 * Add a new {@link Updatable} or {@link Renderable} to the list of updating
	 * and rendering entities.
	 * 
	 * @param e
	 *            - The {@link Updatable} or {@link Renderable} to be added to
	 *            the list
	 */
	public void add(E e) {
		if (!all.contains(e)) {
			toBeAdded.add(e);
		}
	}

	/**
	 * Removes an {@link Updatable} or {@link Renderable} from the list of
	 * updating and rendering entities.
	 * 
	 * @param e
	 *            - The {@link Updatable} or {@link Renderable} to be removed
	 *            from the list
	 */
	public void remove(E e) {
		if (all.contains(e)) {
			toBeRemoved.add(e);
		}
	}

	/**
	 * Clears all {@link Updatable}s or {@link Renderable}s from the list of
	 * updating and rendering {@link Updatable}s or {@link Renderable}s.
	 */
	public void clear() {
		toBeRemoved.addAll(all);
	}

	/**
	 * Get every object within this {@link Container}.
	 * 
	 * @return all - ArrayList of all elements
	 */
	public ArrayList<E> getAll() {
		return all;
	}
	
	public E getById(String id) {
		for (E e : all) {
			if (e instanceof Entity) {
				if (((Entity) e).getId().equals(id)) {
					return e;
				}
			}
		}
		throw new NullPointerException("Object was not found by ID");
	}

}
