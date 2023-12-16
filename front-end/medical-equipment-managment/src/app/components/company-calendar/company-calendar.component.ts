import { Component, OnInit } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import { DateTime } from 'luxon';
import { Subscription } from 'rxjs';
import { AppComponent } from 'src/app/app.component';
import { AppointmentCreate } from 'src/app/model/appointment-create.model';
import { Appointment } from 'src/app/model/appointment.model';
import { Company } from 'src/app/model/company';
import { CompanyAdmin } from 'src/app/model/company-admin.model';
import { CurrentUser } from 'src/app/model/current-user';
import { AdminService } from 'src/app/services/admin.service';
import { AppointmentService } from 'src/app/services/appointment.service';
import { AuthService } from 'src/app/services/auth.service';
import { Time } from '@angular/common';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-company-calendar',
  templateUrl: './company-calendar.component.html',
  styleUrls: ['./company-calendar.component.css'],
  providers: [DatePipe],
})
export class CompanyCalendarComponent implements OnInit {
  user: CurrentUser | undefined;
  subscription: Subscription;
  company: Company = {
    id: 0,
    averageGrade: 0,
    city: '',
    country: '',
    description: '',
    name: '',
    startTime: { hours: 10, minutes: 10 },
    endTime: { hours: 10, minutes: 10 },
  };
  admin: CompanyAdmin = {
    id: 0,
    firstName: '',
    lastName: '',
    password: '',
    email: '',
    city: '',
    country: '',
    phoneNumber: '',
    companyId: 0,
  };
  admins: CompanyAdmin[] = [];
  selectedAdmin: CompanyAdmin = {
    id: 0,
    firstName: '',
    lastName: '',
    password: '',
    email: '',
    city: '',
    country: '',
    phoneNumber: '',
    companyId: 0,
  };
  selectedDateTime: DateTime;
  appointmentForCreation: AppointmentCreate = {
    adminsId: 0,
    dateTime: DateTime.now(),
    duration: 30,
  };
  todaysDate: String = DateTime.now().toISO();

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin, timeGridPlugin],
    events: [],
    themeSystem: 'custom',
    eventClick: this.handleEventClick,
    headerToolbar: {
      left: 'prev,next today', // Include navigation buttons
      center: 'title', // Display the title in the center
      right: 'timeGridWeek,dayGridMonth,dayGridYear', // Add buttons for different views
    },
  };
  //eventClick: (clickInfo: any) => this.handleEventClick(clickInfo);

  constructor(
    private authService: AuthService,
    private adminService: AdminService,
    private appointmentService: AppointmentService,
    public datePipe: DatePipe
  ) {}

  ngOnInit(): void {
    this.subscription = this.authService.currentUser.subscribe((user) => {
      this.user = user;
      console.log(this.user.id);
      this.todaysDate = this.todaysDate.split('T')[0];
      this.todaysDate += 'T00:00';
    });
    setTimeout(() => {
      this.loadAdmin();
    }, 100);
    const eventsList: any[] = [
      {
        title: 'Meeting with Client', // Updated event title
        start: '2023-12-01T08:00:00', // Set start time to 8:00 AM
        end: '2023-12-01T09:30:00', // Set end time to 9:30 AM
        color: 'green',
        description: 'Discuss project details', // Additional event property
      },
      {
        title: 'Meeting with Client 2', // Updated event title
        start: '2023-12-01T10:30:00', // Set start time to 10:30 AM
        end: '2023-12-01T12:00:00', // Set end time to 12:00 PM
        color: 'green',
        description: 'Discuss project details', // Additional event property
      },
      {
        title: 'Team Lunch', // Updated event title
        start: '2023-12-05T10:00:00',
        end: '2023-12-05T10:00:00', // Set end time same as start time
        color: 'blue',
        description: 'Bonding session', // Additional event property
      },
      // Add more event objects as needed...
    ];

    // Assign the eventsList to calendarOptions.events
    this.calendarOptions.events = eventsList;
  }
  handleEventClick(clickInfo: any): void {
    const eventTitle = clickInfo.event.title || 'No title';
    const eventDescription =
      clickInfo.event.extendedProps.description || 'No description';

    alert(
      `Event clicked:\nTitle: ${eventTitle}\nDescription: ${eventDescription}`
    );
    // For example, open a modal, navigate to a different page, etc.
  }

  loadAdmin() {
    this.adminService.getAdmin(this.user!.id).subscribe({
      next: (result: CompanyAdmin) => {
        this.admin = result;
        this.loadCompany();
      },
    });
  }

  loadCompany() {
    this.adminService.getCompanyForAdmin(this.admin.id).subscribe({
      next: (result: Company) => {
        this.company = result;
        this.loadAllAdmins();
      },
    });
  }

  loadAllAdmins() {
    this.adminService.getAdmins(this.company.id).subscribe({
      next: (result: CompanyAdmin[]) => {
        this.admins = result;
        console.log(this.company.startTime, this.company.endTime);
      },
    });
  }

  addSlot() {
    // let asf = this.datePipe.transform(
    //   this.selectedDateTime.toJSDate(),
    //   'dd-MM-YYYY HH:MM'
    // );
    console.log(this.todaysDate, this.selectedDateTime);
    if (this.selectedAdmin.id == 0) {
      alert('Select an admin!');
      return;
    }
    let selectedTimeString = this.selectedDateTime
      .toLocaleString()
      .split('T')[1];
    let selectedHour = selectedTimeString.split(':')[0];
    let selectedMinutes = selectedTimeString.split(':')[1];
    let workingStartHour = this.company.startTime
      .toLocaleString()
      .split(':')[0];
    let workingStartMinutes = this.company.startTime
      .toLocaleString()
      .split(':')[1];
    let workingEndHour = this.company.endTime.toLocaleString().split(':')[0];
    let workingEndMinutes = this.company.endTime.toLocaleString().split(':')[1];
    console.log(
      'selektovano',
      selectedHour,
      selectedMinutes,
      'radno',
      workingStartHour,
      workingStartMinutes,
      workingEndHour,
      workingEndMinutes
    );
    if (selectedHour < workingStartHour || selectedHour > workingEndHour) {
      alert('We are closed in that time!');
      return;
    }
    if (selectedHour == workingStartHour) {
      if (selectedMinutes < workingStartMinutes) {
        alert('We are closed in that time!');
        return;
      }
    }
    if (selectedHour == workingEndHour) {
      if (selectedMinutes > workingEndMinutes) {
        alert('We are closed in that time!');
        return;
      }
    }
    this.appointmentForCreation.adminsId = this.selectedAdmin.id;
    this.appointmentForCreation.dateTime = this.selectedDateTime;
    this.appointmentService
      .addAppointment(this.appointmentForCreation)
      .subscribe({
        next: (result: Appointment) => {
          console.log(result);
          this.selectedAdmin.id = 0;
        },
      });
  }
}
