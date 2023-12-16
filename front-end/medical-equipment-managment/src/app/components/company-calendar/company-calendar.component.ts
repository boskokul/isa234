import { Component, OnInit } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';

@Component({
  selector: 'app-company-calendar',
  templateUrl: './company-calendar.component.html',
  styleUrls: ['./company-calendar.component.css']
})
export class CompanyCalendarComponent implements OnInit {
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin, timeGridPlugin],
    events: [],
    themeSystem: 'custom',
    eventClick: this.handleEventClick,
    headerToolbar: {
      left: 'prev,next today', // Include navigation buttons
      center: 'title', // Display the title in the center
      right: 'timeGridWeek,dayGridMonth,dayGridYear' // Add buttons for different views
    },
  };
  //eventClick: (clickInfo: any) => this.handleEventClick(clickInfo);

  ngOnInit(): void{
    const eventsList: any[] = [
      {
        title: 'Meeting with Client', 
        start: '2023-12-01T08:00:00', 
        end: '2023-12-01T09:30:00', 
        color: 'green',
        description: 'Discuss project details',
        id: 'hehe',
      },
      {
        title: 'Meeting with Client 2', 
        start: '2023-12-01T10:30:00', 
        end: '2023-12-01T12:00:00', 
        color: 'green',
        description: 'Discuss project details',
        id: 'hehe',
      },
      {
        title: 'Team Lunch', 
        start: '2023-12-05T10:00:00',
        end: '2023-12-05T10:00:00', 
        color: 'red',
        description: 'Bonding session',
        id: 'hehe',
      }
    ];

    // Assign the eventsList to calendarOptions.events
    this.calendarOptions.events = eventsList;
  };
    handleEventClick(clickInfo: any): void {
      const eventTitle = clickInfo.event.title || 'No title';
      const eventDescription = clickInfo.event.extendedProps.description || 'No description';
      const eventId = clickInfo.event.extendedProps.id || 'No id';
    
      alert(`Event clicked:\nTitle: ${eventTitle}\nDescription: ${eventDescription}\nEventId: ${eventId}`);
      // For example, open a modal, navigate to a different page, etc.
    }
  
  constructor(){

  }

}
