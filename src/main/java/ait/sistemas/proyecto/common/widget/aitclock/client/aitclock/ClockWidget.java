package ait.sistemas.proyecto.common.widget.aitclock.client.aitclock;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;

public class ClockWidget extends HTML {

	public static final String CLASSNAME = "clockcomponent";
	private long time = 0;
	private Timer timer = null;
	private Date date = new Date();

	private String[] es_mount= {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	private String[] en_mount= {"June", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	private String[] es_day = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
	private String[] en_day = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	private final DateTimeFormat dateFormat = DateTimeFormat.getFormat("EEEE, dd MMMM, yyyy");
	private final DateTimeFormat timeFormat = DateTimeFormat.getFormat("hh:mm:ss");
	private final DateTimeFormat amFormat = DateTimeFormat.getFormat("a");

	private String dateHtml = null;
	private String timeHtml = null;
	private String html = null;

	public ClockWidget() {

		// setText("ClockComponent sets the text via ClockComponentConnector using ClockComponentState");
		setStyleName(CLASSNAME);

		timer = new Timer(){
			@Override
			public void run() {
				time = time + 1000;
				display();
			}
		};
		timer.scheduleRepeating(1000);
	}
	public void setTime(long t){
		time = t;
	}
	private void display(){
		date.setTime(time);;
		
		String strDate =  dateFormat.format(date).toString();
		
		for (int i=0; i < 7; i++){
			strDate = strDate.replaceAll(this.en_day[i], this.es_day[i]);
		}
		for (int i=0; i < 12; i++){
			strDate = strDate.replaceAll(this.en_mount[i], this.es_mount[i]);
		}
		dateHtml = "<div align=\"center\">" +
				"<span style=\"font-size:11pt;\">" + strDate + "</span>" +
				"</div>";

		timeHtml = "<div align=\"center\">" +
				"<span style=\"color:#000000; font-weight:bold; font-size:20pt;\">" + timeFormat.format(date) + "</span>" +
				"&nbsp;<span style=\"color:#000000; font-size:12pt;\">" + amFormat.format(date) + "</span>" +
				"</div>";

		html =  dateHtml + timeHtml ;

		setHTML(html);
	}
}