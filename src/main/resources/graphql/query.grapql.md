## QUERY 
colocar el token o authorization siempre en las consults
```
{
  "authorization" : "bearer $token"
}
```
### Obtener los USUARIOS y sus roles
```query Usuarios {
  usuarios {
    id
    username
    role{roleName}
  }
}
```
### obtener los productos por ID
```
query GetProductById {
  product(id: "665795ba2db644308721abd8") {
    name
  },
}
```
### obtener TODOS los productos
```
query GetAllProducts {
  products{
    id
    name
    price
    imageUrl
    description
  },
}
```

### Obtener TODAS las compras
```
query Purchases{
  purchases {
    id
    idCliente
    precioTotal
  }
}
```
### OBTENER TODOS los proveedores
```
query {
  suppliers{
    id
    name
    nit
    telefono
  }
}
```



# MUTATION 

```
mutation CreateProduct{
  createProduct(productInput: {
    name: "Giragril",
    price: 55.25,
    imageUrl: "http://imgaee.opee",
    description:"una gran descripcion"
  }){
    name,
    price,
    imageUrl,
    description
  }
}
```
```
mutation UpdateProduct {
  updateProduct(id:"665f40b4b3f37b2cf45aff7c", productInput:{
    name:"Hierro fundido"
    imageUrl:"http://hierro.png"
    price: 55.5
    description: "unaasdfasdfsdf"
  }){
    name
  }
}
```
```
mutation DeleteProductById {
  deleteProductById(id:"665f40b4b3f37b2cf45aff7c")
}
```
##  Login 
```
mutation Login {
  login(loginInput: {username: "andres", password: "1234"}) {
    username
    token
    message
    status
  }
}
```

## Register 
```
mutation {
  register(
    inputAuthCreateUserRequest: {username: "julio", password: "1234", roleRequest: "ADMIN"}
  ) {
    username
  }
}
```
## Compras
### Añadir una compra
```
mutation addPurchase{
  addPurchase(purchaseInput: {
    idCliente: "6663add3f1d1df7460d51e3b"
    precioTotal: 85.5
  }){
    id
    idCliente
    precioTotal
  }
}
```
### Actualizar una compra
```
mutation update {
  updatePurchase(id:"666938936ee0fd3b4690705b", purchaseInput:{
    precioTotal: 350.0
  }){
    id
    idCliente
    precioTotal
  }
}
```
### Eliminar una COMPRA
```
mutation deletePurchase {
  deletePurchaseById(id:"666938936ee0fd3b4690705b")
}
```
## DETALLE DE COMPRA
### AGREGAR un detalle de COMPRA
```
mutation {
  addDetailPurchase(detailPurchaseInput:{
    idProducto: "66645b5a891568055f04b462"
    cantidad: 8
    precioProducto:350
  }){
    id
    idProducto
    cantidad
    precioProducto
  }
}
```
### ACTUALIZAR un DETALLE DE COMPRA
```
mutation {
  updateDetailPurchase(
    id:"6669426f6ee0fd3b46907068",
    detailPurchaseInput:
    {
    idProducto: "6669426f6ee0fd3b46907068"
    cantidad: 7
    precioProducto: 85
  })
  {
    id
    idProducto
    cantidad
    precioProducto    
  }
}
```
### ELIMINAR un DETALLE DE COMPRA
```
mutation {
  deleteDetailPurchaseById(id:"666942886ee0fd3b46907069")
}
```

## PROVEEDOR
### AGREGAR un PROVEEDOR
```
mutation {
  createSupplier(supplierInput:{
    name : "Anita"
    nit: "3243255"
    telefono: "7788888"
  }){
    id
    name
    nit
    telefono
  }
}
```
### ACTUALIZAR un PROVEEDOR
```
mutation {
  updateSupplier(id: "66694b63a8aa024a85a779a7", supplierInput:{
    name : "Pedro"
    nit: "3243255"
    telefono: "7788888"
  }){
    id
    name
    nit
    telefono
  }
}
```
### ELIMINAR un PROVEEDOR
```
mutation {
  deleteSupplierById(id:"66694b63a8aa024a85a779a7")
}
```