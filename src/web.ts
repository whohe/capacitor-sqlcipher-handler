import { WebPlugin } from '@capacitor/core';

import type { SqlcipherHandlerPlugin } from './definitions';
import { Response } from './definitions';


export class SqlcipherHandlerWeb
  extends WebPlugin
  implements SqlcipherHandlerPlugin
{
  async openDb(options: { dbName: string, password: string }): Promise<Response> {
  	options;
  	return {response: {status: 'OK',msg: 'OK',data: 'OK'}}
  }
  async dbStatus(): Promise<Response> {
  	return {response: {status: 'OK',msg: 'OK',data: 'OK'}}
  }
  async execute(options: { query: string }): Promise<Response> {
  	options;
  	return {response: {status: 'OK',msg: 'OK',data: 'OK'}}
  }
  async select(options: { query: string }): Promise<Response> {
  	options;
  	return {response: {status: 'OK',msg: 'OK',data: 'OK'}}
  }
}
