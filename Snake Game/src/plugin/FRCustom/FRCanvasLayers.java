package plugin.FRCustom;

public class FRCanvasLayers {
	private FRCanvasLayer[] layers = new FRCanvasLayer[0];
	
	public FRCanvasLayer[] getLayers() {
		return layers;
	}
	
	/**
	 * Formats Layout into one big Layer.
	 */
	public FRCanvasLayer getCombinedLayer() {
		FRCanvasLayer All_layers = new FRCanvasLayer();
		for (int i = 0; i < layers.length; i++) {
			All_layers.addtoTop(layers[i].getShapes());
		}
		return All_layers;
	}
	
	/**
	 * Sets inputted Layer as the very top Layer.
	 */
	public void settoTopLayer(FRCanvasLayer layer) {
	 FRCanvasLayer[] layers_copy = new FRCanvasLayer[this.layers.length+1];
	   for (int i = 0; i < this.layers.length; i++) {
	       layers_copy[i] = layers[i];
	   }
	   layers_copy[this.layers.length] = layer;
	   this.layers = layers_copy;
	}
	
	/**
	 * Sets inputted Layer as the very bottom Layer.
	 */
	public void settoBottomLayer(FRCanvasLayer layer) {
	    FRCanvasLayer[] layers_copy = new FRCanvasLayer[this.layers.length+1];
	    layers_copy[0] = layer;
	    for (int i = 0; i < this.layers.length; i++) {
	        layers_copy[i+1] = layers[i];
	    }
	    this.layers = layers_copy;
		   
	}
}
