# ADR-001 Table Primary Key Naming

## Problem

We should find a proper naming for tables and primary keys.
Also we should find a way to verify it? Can we automate this or we can put this in to a process such as code review.

## Suggestions

Table name format should be feature domain name as snake case . Product Group = product_group

if domain entity contains single word use first three consonants plus id prefix. example product = prd_id.
if domain entity contains two word use first consonants from first part plus use first two consonants from second part plus id. example product_group = pgr_id
if domain entity contains three words use first consonant from first, second and third part. example product_group_owner = pgo_id.

## Decision
Date : **2020-12-13**
Naming **accepted**. This rule will be verified at code review process.
