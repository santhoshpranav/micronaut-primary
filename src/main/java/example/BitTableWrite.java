package example;

import javax.inject.Singleton;

/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.data.v2.models.RowMutation;
import com.google.protobuf.ByteString;

@Singleton
public class BitTableWrite {
  private static final String COLUMN_FAMILY_NAME = "pets_summary";

  public static void writeSimple() {
    String projectId = "abc";
    String instanceId = "test-id";
    String tableId = "pets";

    try (BigtableDataClient dataClient = BigtableDataClient.create(projectId, instanceId)) {
      long timestamp = System.currentTimeMillis() * 1000;

      String rowkey = "pet#4c410523#20190501";

      RowMutation rowMutation =
          RowMutation.create(tableId, rowkey)
              .setCell(
                  COLUMN_FAMILY_NAME,
                  "name",
                  timestamp,
                  "Dino")
              .setCell(
                  COLUMN_FAMILY_NAME,
                  "type",
                  timestamp,
                  "Dog"); 

      dataClient.mutateRow(rowMutation);
      
      rowkey = "pet#4c410523#20190502";
      RowMutation rowMutation1 =
              RowMutation.create(tableId, rowkey)
                  .setCell(
                      COLUMN_FAMILY_NAME,
                      "name",
                      timestamp,
                      "Meow")
                  .setCell(
                      COLUMN_FAMILY_NAME,
                      "type",
                      timestamp,
                      "Cat"); 

          dataClient.mutateRow(rowMutation1);
      System.out.printf("Successfully wrote row %s", rowkey);

    } catch (Exception e) {
      System.out.println("Error during WriteSimple: \n" + e.toString());
    }
  }
}

// [END bigtable_writes_simple]
