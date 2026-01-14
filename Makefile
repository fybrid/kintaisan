.PHONY: help flyway-info flyway-migrate flyway-clean flyway-validate flyway-repair

-include .env

MVNW := ./mvnw
DB_HOST ?= localhost
DB_PORT ?= 5432
DB_NAME ?= kintaisan
DB_USERNAME ?=
DB_PASSWORD ?=

define require-var
	@if [ -z "$($1)" ]; then \
		echo "Missing required variable: $1"; \
		echo "Example: $1=your_value make $@"; \
		exit 1; \
	fi
endef

help:
	@echo "Flyway commands:"
	@echo "  make flyway-info DB_USERNAME=... DB_PASSWORD=..."
	@echo "  make flyway-migrate DB_USERNAME=... DB_PASSWORD=..."
	@echo "  make flyway-clean DB_USERNAME=... DB_PASSWORD=... CONFIRM=yes"
	@echo "Vars: DB_HOST=$(DB_HOST) DB_PORT=$(DB_PORT) DB_NAME=$(DB_NAME)"
	@echo "If .env exists, DB_USERNAME/DB_PASSWORD are loaded automatically."

flyway-info:
	$(call require-var,DB_USERNAME)
	$(call require-var,DB_PASSWORD)
	$(MVNW) -Dflyway.url=jdbc:postgresql://$(DB_HOST):$(DB_PORT)/$(DB_NAME) \
		-Dflyway.user=$(DB_USERNAME) \
		-Dflyway.password=$(DB_PASSWORD) \
		flyway:info

flyway-migrate:
	$(call require-var,DB_USERNAME)
	$(call require-var,DB_PASSWORD)
	$(MVNW) -Dflyway.url=jdbc:postgresql://$(DB_HOST):$(DB_PORT)/$(DB_NAME) \
		-Dflyway.user=$(DB_USERNAME) \
		-Dflyway.password=$(DB_PASSWORD) \
		flyway:migrate

flyway-clean:
	$(call require-var,DB_USERNAME)
	$(call require-var,DB_PASSWORD)
	@if [ "$(CONFIRM)" != "yes" ]; then \
		echo "Refusing to run flyway:clean without CONFIRM=yes"; \
		exit 1; \
	fi
	$(MVNW) -Dflyway.url=jdbc:postgresql://$(DB_HOST):$(DB_PORT)/$(DB_NAME) \
		-Dflyway.user=$(DB_USERNAME) \
		-Dflyway.password=$(DB_PASSWORD) \
		-Dflyway.cleanDisabled=false \
		flyway:clean

flyway-validate:
	$(call require-var,DB_USERNAME)
	$(call require-var,DB_PASSWORD)
	$(MVNW) -Dflyway.url=jdbc:postgresql://$(DB_HOST):$(DB_PORT)/$(DB_NAME) \
		-Dflyway.user=$(DB_USERNAME) \
		-Dflyway.password=$(DB_PASSWORD) \
		flyway:validate

flyway-repair:
	$(call require-var,DB_USERNAME)
	$(call require-var,DB_PASSWORD)
	$(MVNW) -Dflyway.url=jdbc:postgresql://$(DB_HOST):$(DB_PORT)/$(DB_NAME) \
		-Dflyway.user=$(DB_USERNAME) \
		-Dflyway.password=$(DB_PASSWORD) \
		flyway:repair
