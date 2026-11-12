# Jardda League - Modelo MongoDB

Pedi a IA para que ela montasse esse arquivo MD para facilitar o entendimento do que foi feito da migração do Mongo.

O banco foi convertido diretamente do PostgreSQL (12 tabelas viram 6 coleções). O que era tabela de ligação (`clube_liga`, `clube_jogador`, `clube_tecnico`) ou relação 1:1 (`estatistica_partida`) ficou embutido. `pais` e `cidade` viram subdocumentos.

Notação: o valor de cada campo é o tipo BSON. `Tipo | null` indica campo opcional. O `_id` guarda o id original do PostgreSQL.

## clubes

```json
{
  "_id": "Int32",

  "nome_clube": "String",

  "cidade": {
    "nome_cidade": "String",
    "pais": {
      "nome_pais": "String",
      "sigla": "String"
    }
  },

  "ligas": [
    {
      "liga_id": "Int32",
      "nome_liga": "String",
      "periodo_entrada": "Date",
      "periodo_saida": "Date | null"
    }
  ]
}
```

`periodo_saida` null = ainda está na liga.

## ligas

```json
{
  "_id": "Int32",

  "nome_liga": "String",
  "categoria_liga": "String | null",
  "estilo_liga": "String",

  "pais": {
    "nome_pais": "String",
    "sigla": "String"
  }
}
```

`pais` pode ser null. `categoria_liga`: municipal, estadual, regional, nacional, internacional. `estilo_liga`: mata_mata, pontos_corridos, amistoso, tira_teima, misto, grupos.

## estadios

```json
{
  "_id": "Int32",

  "nome_estadio": "String",

  "cidade": {
    "nome_cidade": "String",
    "pais": {
      "nome_pais": "String",
      "sigla": "String"
    }
  },

  "clube": {
    "clube_id": "Int32",
    "nome_clube": "String"
  }
}
```

`clube` pode ser null (estádio compartilhado).

## tecnicos

```json
{
  "_id": "Int32",

  "nome_tecnico": "String",
  "aniversario_tecnico": "Date",

  "pais": {
    "nome_pais": "String",
    "sigla": "String"
  },

  "contratos": [
    {
      "clube_id": "Int32",
      "nome_clube": "String",
      "inicio_contrato": "Date",
      "previsao_contrato": "Date",
      "termino_contrato": "Date | null",
      "valor_salario": "Decimal128"
    }
  ]
}
```

`termino_contrato` null = contrato ativo. `valor_salario` é anual, em dólares.

## jogadores

```json
{
  "_id": "Int32",

  "nome_jogador": "String",
  "aniversario_jogador": "Date",
  "posicao": "String",
  "lesionado": "Boolean",

  "pais": {
    "nome_pais": "String",
    "sigla": "String"
  },

  "contratos": [
    {
      "clube_id": "Int32",
      "nome_clube": "String",
      "inicio_contrato": "Date",
      "previsao_contrato": "Date",
      "termino_contrato": "Date | null",
      "valor_salario": "Decimal128"
    }
  ]
}
```

`posicao`: ZAG, GOL, ATC, MEI, LAT, VOL. `termino_contrato` null = contrato ativo. `valor_salario` é anual, em dólares.

## partidas

```json
{
  "_id": "Int32",

  "data_partida": "Date",
  "publico_partida": "Int32",
  "rodada_partida": "Int32 | null",

  "mandante": {
    "clube_id": "Int32",
    "nome_clube": "String"
  },

  "visitante": {
    "clube_id": "Int32",
    "nome_clube": "String"
  },

  "liga": {
    "liga_id": "Int32",
    "nome_liga": "String"
  },

  "estadio": {
    "estadio_id": "Int32",
    "nome_estadio": "String"
  },

  "estatisticas": {
    "mandante": {
      "gols": "Int32",
      "cartoes": "Int32",
      "chutes": "Int32"
    },
    "visitante": {
      "gols": "Int32",
      "cartoes": "Int32",
      "chutes": "Int32"
    }
  }
}
```

`estadio` e `estatisticas` podem ser null. `mandante` é o antigo `time_1` e `visitante` o `time_2`.
