// Created by plusminus on 20:32:01 - 27.09.2008
package de.blau.android.views.overlay;


import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import de.blau.android.Map;
import de.blau.android.views.IMapView;

/**
 * Base class representing an overlay which may be displayed on top of an
 * {@link IMapView}. To add an overlay, subclass this class, create an
 * instance, and add it to the list obtained from getOverlays() of
 * {@link Map}.
 * <br/>
 * This class was taken from OpenStreetMapViewer (original package org.andnav.osm) in 2010-06
 * by Marcus Wolschon to be integrated into the de.blau.androin
 * OSMEditor. 
 * @author Nicolas Gramlich
 * @author Marcus Wolschon <Marcus@Wolschon.biz>
 */
public abstract class MapViewOverlay {
    /**
     * Tag used for Android-logging.
     */
	private static final String DEBUG_TAG = MapTilesOverlay.class.getName();

    // ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for SuperClass/Interfaces
	// ===========================================================

	/**
	 * Managed Draw calls gives Overlays the possibility to first draw manually
	 * and after that do a final draw. This is very useful, i sth. to be drawn
	 * needs to be <b>topmost</b>.
	 * 
	 * @param c Canvas to draw on to
	 * @param osmv view alling us
	 */
	@SuppressLint("WrongCall")
	public void onManagedDraw(final Canvas c, final IMapView osmv) {
		try {
			if (isReadyToDraw()) {
				onDraw(c, osmv);
				onDrawFinished(c, osmv);
			}
		} catch (Exception e) {
			Log.e(DEBUG_TAG, "Exception while drawing map", e);
		}
	}

	protected abstract void onDraw(final Canvas c, final IMapView osmv);

	protected abstract void onDrawFinished(final Canvas c, final IMapView osmv);

	// ===========================================================
	// Methods
	// ===========================================================
	
	protected boolean isReadyToDraw() {
		return true;
	}
	
	/**
	 * By default does nothing.
	 */
	public void onDestroy() {
	}
	
	/**
	 * By default does nothing.
	 */
	public void onLowMemory() {
	}
	
	/**
	 * By default does nothing (<code>return false</code>). If you handled the
	 * Event, return <code>true</code>, otherwise return <code>false</code>. If
	 * you returned <code>true</code> none of the following Overlays or the
	 * underlying {@link IMapView} has the chance to handle this event.
	 * 
	 * @param keyCode the keycode to handle
	 * @param event the event
	 * @param mapView the view that got the event
	 * @return true if event was handled
	 */
	public boolean onKeyDown(final int keyCode, KeyEvent event,
			final IMapView mapView) {
		return false;
	}

	/**
	 * By default does nothing (<code>return false</code>). If you handled the
	 * Event, return <code>true</code>, otherwise return <code>false</code>. If
	 * you returned <code>true</code> none of the following Overlays or the
	 * underlying {@link IMapView} has the chance to handle this event.
	 * 
	 * @param keyCode the keycode to handle
	 * @param event the event
	 * @param mapView the view that got the event
	 * @return true if event was handled
	 */
	public boolean onKeyUp(final int keyCode, KeyEvent event,
			final IMapView mapView) {
		return false;
	}

	/**
	 * <b>You can prevent all(!) other Touch-related events from happening!</b>
	 * 
	 * By default does nothing (<code>return false</code>). If you handled the
	 * Event, return <code>true</code>, otherwise return <code>false</code>. If
	 * you returned <code>true</code> none of the following Overlays or the
	 * underlying {@link IMapView} has the chance to handle this event.
	 * 
	 * @param event the touch event
	 * @param mapView the view that got the event
	 * @return true if event was handled
	 */
	public boolean onTouchEvent(final MotionEvent event,
			final IMapView mapView) {
		return false;
	}

	/**
	 * By default does nothing (<code>return false</code>). If you handled the
	 * Event, return <code>true</code>, otherwise return <code>false</code>. If
	 * you returned <code>true</code> none of the following Overlays or the
	 * underlying {@link IMapView} has the chance to handle this event.
	 */
	/**
	 * By default does nothing (<code>return false</code>). If you handled the
	 * Event, return <code>true</code>, otherwise return <code>false</code>. If
	 * you returned <code>true</code> none of the following Overlays or the
	 * underlying {@link IMapView} has the chance to handle this event.
	 * 
	 * @param event the trackball event
	 * @param mapView the view that got the event
	 * @return true if event was handled
	 */
	public boolean onTrackballEvent(final MotionEvent event,
			final IMapView mapView) {
		return false;
	}

	/**
	 * By default does nothing (<code>return false</code>). If you handled the
	 * Event, return <code>true</code>, otherwise return <code>false</code>. If
	 * you returned <code>true</code> none of the following Overlays or the
	 * underlying {@link IMapView} has the chance to handle this event.
	 * 
	 * @param e the motion event
	 * @param mapView the view that got the event
	 * @return true if event was handled
	 */
	public boolean onSingleTapUp(MotionEvent e,
			IMapView mapView) {
		return false;
	}

	/**
	 * By default does nothing (<code>return false</code>). If you handled the
	 * Event, return <code>true</code>, otherwise return <code>false</code>. If
	 * you returned <code>true</code> none of the following Overlays or the
	 * underlying {@link IMapView} has the chance to handle this event.
	 * 
	 * @param e the motion event
	 * @param mapView the view that got the event
	 * @return true if event was handled
	 */
	public boolean onLongPress(MotionEvent e,
			IMapView mapView) {
		return false;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	/**
	 * Interface definition for overlays that contain items that can be snapped
	 * to (for example, when the user invokes a zoom, this could be called
	 * allowing the user to snap the zoom to an interesting point.)
	 */
	public interface Snappable {
		
		/**
		 * Checks to see if the given x and y are close enough to an item resulting in snapping the current action (e.g. zoom) to the item. 
		 * 
		 * @param x The x in screen coordinates.
		 * @param y The y in screen coordinates.
		 * @param snapPoint To be filled with the the interesting point (in screen coordinates) that is closest to the given x and y. Can be untouched if not snapping.
		 * @param mapView The IMapView that is requesting the snap. Use MapView.getProjection() to convert between on-screen pixels and latitude/longitude pairs. 
		 * @return Whether or not to snap to the interesting point.
		 */
		boolean onSnapToItem(int x, int y, android.graphics.Point snapPoint,
				IMapView mapView);
	}

}
