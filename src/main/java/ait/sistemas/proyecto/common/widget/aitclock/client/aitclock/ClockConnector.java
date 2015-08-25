package ait.sistemas.proyecto.common.widget.aitclock.client.aitclock;

import ait.sistemas.proyecto.common.widget.aitclock.AitClock;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(AitClock.class)
public class ClockConnector extends AbstractComponentConnector {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ClockServerRpc rpc = RpcProxy
            .create(ClockServerRpc.class, this);

	private Timer timer = null;

    public ClockConnector() {    
		timer = new Timer(){
			@Override
			public void run() {
				rpc.getServerTime();
			}
		};
		timer.scheduleRepeating(5*60*1000);
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(ClockWidget.class);
    }

    @Override
    public ClockWidget getWidget() {
        return (ClockWidget) super.getWidget();
    }

    @Override
    public ClockState getState() {
        return (ClockState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
		
        final long time = getState().time;
        getWidget().setTime(time);
    }
}