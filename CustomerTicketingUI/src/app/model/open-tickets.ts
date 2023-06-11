export class OpenTickets {
  
        ticketId!: number;
        subject!: string;
        description!: string;
        priority!: string;
        status!: string;
        customer!: {
          customerid: number;
          username: string;
        };
        
}
