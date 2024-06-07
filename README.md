# capacitor-sqlcipher-handler

sqlcipher database manager for capacitor

## Install

```bash
npm install capacitor-sqlcipher-handler
npx cap sync
```

## API

<docgen-index>

* [`openDb(...)`](#opendb)
* [`dbStatus()`](#dbstatus)
* [`execute(...)`](#execute)
* [`select(...)`](#select)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### openDb(...)

```typescript
openDb(options: { dbName: string; password: string; }) => Promise<Response>
```

| Param         | Type                                               |
| ------------- | -------------------------------------------------- |
| **`options`** | <code>{ dbName: string; password: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#response">Response</a>&gt;</code>

--------------------


### dbStatus()

```typescript
dbStatus() => Promise<Response>
```

**Returns:** <code>Promise&lt;<a href="#response">Response</a>&gt;</code>

--------------------


### execute(...)

```typescript
execute(options: { query: string; }) => Promise<Response>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ query: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#response">Response</a>&gt;</code>

--------------------


### select(...)

```typescript
select(options: { query: string; }) => Promise<Response>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ query: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#response">Response</a>&gt;</code>

--------------------


### Interfaces


#### Response

| Prop           | Type                                                                 |
| -------------- | -------------------------------------------------------------------- |
| **`response`** | <code>{ status: string; msg: string; data: string \| any[]; }</code> |

</docgen-api>
