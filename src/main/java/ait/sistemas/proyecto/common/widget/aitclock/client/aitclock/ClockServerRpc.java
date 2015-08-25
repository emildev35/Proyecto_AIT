package ait.sistemas.proyecto.common.widget.aitclock.client.aitclock;

import com.vaadin.shared.communication.ServerRpc;

public interface ClockServerRpc extends ServerRpc {

    public void getServerTime();
}
