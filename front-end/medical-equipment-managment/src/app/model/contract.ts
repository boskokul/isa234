export interface ContractEquipment{
    name: string;
    quantity: number;
  }
export interface Contract {
    hospitalName: string;
    duration: number;
    dayOfMonth: number;
    hours: number;
    minutes: number;
    status: string;
    contractEquipment: ContractEquipment[];
  }