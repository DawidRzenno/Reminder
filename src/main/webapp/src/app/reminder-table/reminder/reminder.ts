import {ReminderStatus} from './reminder-status.enum';

export class Reminder {
    id: number;
    title: string;
    content: string;
    creationDate: any;
    targetTime: any;
    targetDate: any;
    status: ReminderStatus;
}
