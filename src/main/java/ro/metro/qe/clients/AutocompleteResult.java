package ro.metro.qe.clients;

import java.util.Iterator;
import java.util.List;


public class AutocompleteResult extends Result implements Iterable<Prediction> {

	private List<Prediction> predictions;

	@Override
	public Iterator<Prediction> iterator( ) {
		return this.predictions.iterator( );
	}

	public int size( ) {
		return this.predictions.size( );
	}

}
