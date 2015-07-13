package ait.sistemas.proyecto.common.widget.aitclock;

import java.util.GregorianCalendar;

import ait.sistemas.proyecto.common.widget.aitclock.client.aitclock.ClockServerRpc;
import ait.sistemas.proyecto.common.widget.aitclock.client.aitclock.ClockState;

import com.vaadin.ui.AbstractComponent;

public class AitClock extends AbstractComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClockServerRpc rpc = new ClockServerRpc() {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private GregorianCalendar cal;

        public void getServerTime() {
            // update shared state
        	cal = new GregorianCalendar();
            getState().time = cal.getTimeInMillis();
        }
        
    };  

    public AitClock() {
        registerRpc(rpc);
        rpc.getServerTime();
    }

    @Override
    public ClockState getState() {
        return (ClockState) super.getState();
    }
}
