# capacitor-sqlcipher-handler

Plugin Capacitor para hacer operaciones CRUD en una base encriptada de Sqlite

## Install

* Clona este repositorio en la raiz del proyecto denntro del directorio plugins.
* procede a instalar el proyecto con 
```bash
npm install ./plugins/capacitor-sqlcipher-handler/ --force
```
* Agregar una variable de entorno en los ficheros environment ('./src/environments/environment.prod.ts','src/environments/environment.ts') asi: 
```bash
export const environment = {                                                                                                                                                                                       
  production: true,
  dbName: 'database.db'
};
```
* Crea un servicio en ./src/app/services/sqlcipher-handler.service.ts

```bash
import { Injectable } from '@angular/core';
import { SqlcipherHandler } from 'capacitor-sqlcipher-handler';
import { Platform } from '@ionic/angular';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SqlcipherHandlerService {

  constructor(private platform: Platform) {
                //this.open()
  }

        public async open(secret: string){
                return await SqlcipherHandler.openDb({ dbName: environment.dbName, password: secret });
        }

        public async isOpen() {
                let res = await SqlcipherHandler.dbStatus();
                if (res.response.status=="opened"){
                        return true;
                }else{
                        return false;
                }
        }

        public async execute(query: string) {
                let res = await SqlcipherHandler.execute({query: query});
                return res;
        }

        public async select(query: string) {
                let res = await SqlcipherHandler.select({query: query});
                res.response.data = JSON.parse(res.response.data  as string)
                return res;
        }
}
```

* Ahora puedes implementar el plugin importando el servicio en un componente o pagina de la siguiente forma:

```bash
import { environment } from '../../environments/environment';
import { SqlcipherHandlerService } from '../services/sqlcipher-handler.service';
import { Filesystem, Directory, Encoding } from '@capacitor/filesystem';

@Component({
  selector: 'app-history',
  templateUrl: './history.page.html',
  styleUrls: ['./history.page.scss'],
})
export class HistoryPage implements OnInit {

  constructor(
    private router: Router,
    private db: SqlcipherHandlerService
  ) { } 

  async appIsAlreadyInstalled() {
    await this.db.open(environment.dbName);
    let dbIsOpen = await this.db.isOpen();
    if (dbIsOpen){
      this.db.execute("create table users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT)")                                                                                                    
      this.db.execute("insert into users (name, description) values ('Juan', 'CEO')")
      let out = await this.db.select("select * from users")
      console.log(JSON.stringify(out))

    }   
  }

  ngOnInit() {
    this.appIsAlreadyInstalled();
  }

}
```
