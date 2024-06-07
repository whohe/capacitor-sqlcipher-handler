export interface Response{
  response: {
    status: string;
    msg: string;
    data: any[] | string;
  };
}
export interface SqlcipherHandlerPlugin {
  openDb(options: { dbName: string, password: string }): Promise<Response>;
  dbStatus(): Promise<Response>;
  execute(options: { query: string }): Promise<Response>;
  select(options: { query: string }): Promise<Response>;
}
