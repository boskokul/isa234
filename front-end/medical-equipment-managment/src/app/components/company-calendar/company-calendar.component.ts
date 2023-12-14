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
        title: 'Meeting with Client', // Updated event title
        start: '2023-12-01T08:00:00', // Set start time to 8:00 AM
        end: '2023-12-01T09:30:00', // Set end time to 9:30 AM
        color: 'green',
        description: 'Discuss project details' // Additional event property
      },
      {
        title: 'Meeting with Client 2', // Updated event title
        start: '2023-12-01T10:30:00', // Set start time to 10:30 AM
        end: '2023-12-01T12:00:00', // Set end time to 12:00 PM
        color: 'green',
        description: 'Discuss project details' // Additional event property
      },
      {
        title: 'Team Lunch', // Updated event title
        start: '2023-12-05T10:00:00',
        end: '2023-12-05T10:00:00', // Set end time same as start time
        color: 'blue',
        description: 'Bonding session' // Additional event property
      }
      // Add more event objects as needed...
    ];

    // Assign the eventsList to calendarOptions.events
    this.calendarOptions.events = eventsList;
  };
    handleEventClick(clickInfo: any): void {
      const eventTitle = clickInfo.event.title || 'No title';
      const eventDescription = clickInfo.event.extendedProps.description || 'No description';
    
      alert(`Event clicked:\nTitle: ${eventTitle}\nDescription: ${eventDescription}`);
      // For example, open a modal, navigate to a different page, etc.
    }
  
  constructor(){

  }

}
