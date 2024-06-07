import { registerPlugin } from '@capacitor/core';

import type { SqlcipherHandlerPlugin } from './definitions';

const SqlcipherHandler = registerPlugin<SqlcipherHandlerPlugin>(
  'SqlcipherHandler',
  {
    web: () => import('./web').then(m => new m.SqlcipherHandlerWeb()),
  },
);

export * from './definitions';
export { SqlcipherHandler };
