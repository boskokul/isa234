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
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { ResApp } from 'src/app/model/resApp.model';
import { forkJoin } from 'rxjs';
import { ResNameId } from 'src/app/model/resNameId.model';

@Component({
  selector: 'app-company-calendar',
  templateUrl: './company-calendar.component.html',
  styleUrls: ['./company-calendar.component.css'],
  providers: [DatePipe],
})
export class CompanyCalendarComponent implements OnInit {
  appointmentsAll: Appointment[] = [];

  idFromUrl: number;

  dataSource: any;
  eventsList: any[] = [];

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
    lat: 0,
    lon: 0,
    street: '',
    houseNumber: 0,
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
  nesto: string;
  ids: number[] = [];
  namesRes: ResNameId[] = [];
  pomocna: ResNameId = { fullName: '', idApp: 0 };

  constructor(
    private authService: AuthService,
    private adminService: AdminService,
    private appointmentService: AppointmentService,
    public datePipe: DatePipe,
    private route: ActivatedRoute
  ) {
    this.dataSource = new MatTableDataSource<Appointment>();
  }

  ngOnInit(): void {
    console.log(this.namesRes);
    this.subscription = this.authService.currentUser.subscribe((user) => {
      this.user = user;
      console.log(this.user.id);
      this.todaysDate = this.todaysDate.split('T')[0];
      this.todaysDate += 'T00:00';
    });
    setTimeout(() => {
      this.loadAdmin();
    }, 100);
    this.loadParams();
    this.LoadIds();
    setTimeout(() => {
      this.LoadAppointments();
    }, 1000);
    //console.log(this.dataSource.data);

    console.log(this.eventsList); // Your transformed data in the desired format

    // Assign the eventsList to calendarOptions.events
    this.calendarOptions.events = this.eventsList;
  }
  handleEventClick(clickInfo: any): void {
    const eventTitle = clickInfo.event.title || 'No title';
    const eventDescription =
      clickInfo.event.extendedProps.description || 'No description';
    const eventId = clickInfo.event.extendedProps.id || 'No id';

    /*
    alert(
      `Event clicked:\nTitle: ${eventTitle}\nDescription: ${eventDescription}\nEventId: ${eventId}`
    );
  
    alert(
      `Event clicked:\nTitle: ${eventTitle}\nDescription: ${eventDescription}`
    );
     */
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
    if (
      60 * parseInt(selectedHour) +
        parseInt(selectedMinutes) -
        (60 * parseInt(workingStartHour) + parseInt(workingStartMinutes)) <
      0
    ) {
      alert('We are closed in that time!');
      return;
    }
    if (
      60 * parseInt(selectedHour) +
        parseInt(selectedMinutes) -
        (60 * parseInt(workingEndHour) + parseInt(workingEndMinutes)) >
      -30
    ) {
      alert('We are closed in that time!');
      return;
    }
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
    let flag = false;
    this.appointmentsAll.forEach((a) => {
      if (
        a.adminName ==
        this.selectedAdmin.firstName + ' ' + this.selectedAdmin.lastName
      ) {
        let selectedDate = this.selectedDateTime.toLocaleString().split('T')[0];
        let aDate = a.dateTime.toLocaleString().split('T')[0];
        if (selectedDate == aDate) {
          let aTime = a.dateTime.toLocaleString().split('T')[1];
          let aHour = aTime.split(':')[0];
          let aMinutes = aTime.split(':')[1];
          console.log(aHour, aMinutes, selectedHour, selectedMinutes);
          if (
            60 * parseInt(aHour) +
              parseInt(aMinutes) -
              (60 * parseInt(selectedHour) + parseInt(selectedMinutes)) <
              30 &&
            60 * parseInt(aHour) +
              parseInt(aMinutes) -
              (60 * parseInt(selectedHour) + parseInt(selectedMinutes)) >
              0
          ) {
            flag = true;
          }
          if (
            60 * parseInt(selectedHour) +
              parseInt(selectedMinutes) -
              (60 * parseInt(aHour) + parseInt(aMinutes)) <
              30 &&
            60 * parseInt(selectedHour) +
              parseInt(selectedMinutes) -
              (60 * parseInt(aHour) + parseInt(aMinutes)) >
              0
          ) {
            flag = true;
          }
        }
      }
    });
    if (flag) {
      alert('This slot is already taken by this admin!');
      return;
    }
    this.appointmentForCreation.adminsId = this.selectedAdmin.id;
    this.appointmentForCreation.dateTime = this.selectedDateTime;
    this.appointmentService
      .addAppointment(this.appointmentForCreation)
      .subscribe({
        next: (result: Appointment) => {
          console.log(result);
          this.LoadAppointments();
        },
      });
  }

  LoadIds() {
    this.ids = [];
    this.appointmentService.getAllAppointments(this.idFromUrl).subscribe({
      next: (result: Appointment[]) => {
        this.dataSource.data = result;
        for (const appointment of this.dataSource.data) {
          this.appointmentService
            .getReservationUserByAppointmentId(appointment.id)
            .subscribe({
              next: (result: ResApp) => {
                this.nesto = result.fullName;
                console.log(this.nesto);
                if (this.nesto != '') {
                  this.ids.push(appointment.id);
                  this.pomocna.fullName = this.nesto;
                  this.pomocna.idApp = appointment.id;
                  this.namesRes.push(this.pomocna);
                }
              },
            });
        }
      },
    });
  }

  LoadAppointments() {
    this.appointmentService.getAllAppointments(this.idFromUrl).subscribe({
      next: (result: Appointment[]) => {
        this.dataSource.data = result;
        this.appointmentsAll = result;

        this.eventsList = [];

        console.log('DWWWWW' + this.ids);
        for (const appointment of this.dataSource.data) {
          const startDateTime = new Date(appointment.dateTime);
          let eventItem;
          // console.log(this.namesRes);
          // console.log(appointment.id);
          if (this.ids.includes(appointment.id)) {
            let x = '';
            for (const a of this.namesRes) {
              if (a.idApp == appointment.id) {
                x = a.fullName;
              }
            }
            console.log(x);
            eventItem = {
              title: `${appointment.adminName} - ${x} ${appointment.duration}min`,
              start: startDateTime.toISOString(),
              duration: appointment.duration,
              color: 'red',
              description: `Discuss details for appointment ID: ${appointment.id}`,
              id: `id-${appointment.id}`,
            };
          } else {
            eventItem = {
              title: `${appointment.adminName} (${appointment.duration} min)`,
              start: startDateTime.toISOString(),
              duration: appointment.duration,
              color: 'green',
              description: `Discuss details for appointment ID: ${appointment.id}`,
              id: `id-${appointment.id}`,
            };
          }

          this.eventsList.push(eventItem);
        }

        console.log('Events List:', this.eventsList); // Log the eventsList after construction
        this.calendarOptions.events = this.eventsList;
      },
      error: (err) => {
        console.error('Error fetching appointments:', err);
        // Handle errors here if needed
      },
      complete: () => {
        // Handle completion here if needed
      },
    });
  }

  loadParams(): void {
    this.route.params.subscribe((params) => {
      this.idFromUrl = parseInt(params['id'], 10);
      console.log(this.idFromUrl);
      console.log('ID FETCHED');
    });
  }
}
